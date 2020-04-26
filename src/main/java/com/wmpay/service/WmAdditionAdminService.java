package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weimai.tools.ResponseBean;
import com.weimai.tools.Wm;
import com.weimai.tools.encrypt.Des;
import com.wmpay.bean.*;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.dao.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class WmAdditionAdminService {

    @Autowired
    private WmAdditionAdminDAO wmAdditionAdminDAO;

    @Autowired
    private WmAdditionGroupAccessDAO wmAdditionGroupAccessDAO;

    // 角色组DAO
    @Autowired
    private WmAuthGroupDAO wmAuthGroupDAO;

    @Autowired
    private WmAuthRuleDAO wmAuthRuleDAO;

    @Autowired
    private WmThirdPayConfigDAO thirdPayConfigDAO;

    @Autowired
    private WmWechatPayConfigDAO wechatPayConfigDAO;

    // Request请求
    @Autowired
    private HttpServletRequest request;

    /**
     * 保存新增的代理用户
     *
     * @param wmAdditionAdmin
     * @return
     */
    public Boolean saveAddition(WmAdditionAdmin wmAdditionAdmin) {
        Date date = new Date();
        wmAdditionAdmin.setLogintime(date);
        wmAdditionAdmin.setCreatedTime(date);
        wmAdditionAdmin.setUpdatedTime(date);
        wmAdditionAdmin.setSalt(Wm.getRandom(8));
        wmAdditionAdmin.setStatus("1");
        //wmAdditionAdmin.setUserId(wmAdditionAdmin.getUserId());
        int result = wmAdditionAdminDAO.insert(wmAdditionAdmin);
        if (result > 0) {
            wmAdditionAdmin.setPassword(Des.encode(wmAdditionAdmin.getSalt(), wmAdditionAdmin.getPassword()));
            int upAdmin = wmAdditionAdminDAO.updateById(wmAdditionAdmin);
//            if (upAdmin > 0) {
//                // 新增支付配置
//                WmThirdPayConfig wmPayConfiguration = new WmThirdPayConfig();
//                int thirdPayResult = thirdPayConfigDAO.insert(wmPayConfiguration);
//
//                WmWechatPayConfig wechatPayConfig = new WmWechatPayConfig();
//                wechatPayConfig.setPayType("JSAPI");
//                int wePayResult = wechatPayConfigDAO.insert(wechatPayConfig);
//
//                return thirdPayResult > 0 && wePayResult > 0;
//            }
            return upAdmin > 0;
        }
        return false;
    }


    public Boolean updateAdditionById(WmAdditionAdmin wmAdditionAdmin) {
        // 校验当前密码是否进行修改
        WmAdditionAdmin dbAdditionAdmin = wmAdditionAdminDAO.selectById(wmAdditionAdmin.getWmAdditionAdminId() );
        if (!dbAdditionAdmin.equals(wmAdditionAdmin.getPassword() ) ){
            wmAdditionAdmin.setPassword(Des.encode(wmAdditionAdmin.getSalt(), wmAdditionAdmin.getPassword() ));
        }
        wmAdditionAdmin.setUpdatedTime(new Date() );
        int result = wmAdditionAdminDAO.updateById(wmAdditionAdmin);
        return result > 0;
    }


    /**
     * 触发登陆事件
     *
     * @param wmAdditionAdmin
     * @return
     */
    public ResponseBean login(WmAdditionAdmin wmAdditionAdmin) {
        ResponseBean bean = new ResponseBean();
        QueryWrapper<WmAdditionAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", wmAdditionAdmin.getUsername());
        queryWrapper.eq("type", wmAdditionAdmin.getType());
        WmAdditionAdmin dbWmAdditionAdmin = wmAdditionAdminDAO.selectOne(queryWrapper);
        if (dbWmAdditionAdmin == null) {
            bean.setStatus(-1);
            bean.setTipMsg("登录失败，无当前用户");
            bean.setCusMsg("登录失败，无当前用户");
        } else {
            String salt = dbWmAdditionAdmin.getSalt();
            String saltPassword = Des.encode(salt, wmAdditionAdmin.getPassword());
            if (dbWmAdditionAdmin.getPassword().equals(saltPassword)) {
                request.getSession().setAttribute(AdminCommon.USER_SESSION, dbWmAdditionAdmin);
                // 切换代理模式登陆
                request.getSession().setAttribute(AdminCommon.USER_TYPE, AdminTypeEnum.WM_ADDITION_ADMIN);
                bean.setStatus(1);
                bean.setTipMsg("登录成功");
                bean.setCusMsg("登录成功");
            } else {
                bean.setStatus(-2);
                bean.setTipMsg("登录失败，密码错误");
                bean.setCusMsg("登录失败，密码错误");
            }
        }
        return bean;
    }

    /**
     * 获取代理的菜单视图
     * @param additionAdminId
     * @return
     */
    public String getMenuHTML(Integer additionAdminId) {
        StringBuffer html = new StringBuffer();
        StringBuffer menusBuffer = new StringBuffer();
        // 查询权限分组表
        WmAdditionGroupAccess wmAuthGroupAccess = wmAdditionGroupAccessDAO
                .selectOne(new QueryWrapper<WmAdditionGroupAccess>().eq("wm_addition_admin_id", additionAdminId));
        if (wmAuthGroupAccess != null) {
            WmAuthGroup wmAuthGroup = wmAuthGroupDAO.selectOne(new QueryWrapper<WmAuthGroup>().eq("wm_auth_group_id", wmAuthGroupAccess.getWmAuthGroupId()));
            if (wmAuthGroup != null) {
                // 取出该管理员所拥有的菜单 并转为Integer
                String[] rule = wmAuthGroup.getRules().split("\\|");
                Integer[] ruleId = new Integer[rule.length];
                for (int i = 0; i < rule.length; i++) {
                    ruleId[i] = Integer.parseInt(rule[i]);
                }
                System.out.println(ruleId);
                List<WmAuthRule> parentMenus = wmAuthRuleDAO.getParentMenus(ruleId);
                List<WmAuthRule> menus = wmAuthRuleDAO.getNotParentMenus(ruleId);

                for (int i = 0; i < parentMenus.size(); i++) {
                    int index = 0; // 标识为如果有下级就+1
                    for (int j = 0; j < menus.size(); j++) {
                        if (menus.get(j).getParentId().equals(parentMenus.get(i).getWmAuthRuleId())) {
                            menusBuffer.append("<li><a data-href=\"" + menus.get(j).getName() + "\" data-title=\"" + menus.get(j).getTitle() + "\" href=\"javascript:void(0)\">" + menus.get(j).getTitle() + "</a></li>");
                            index++;
                        }

                    }

                    if (index > 0) {
                        html.append("<dl  class=\"Hui-menu\">");
                        html.append("<dt class=\"Hui-menu-title\"><i class=\"Hui-iconfont\">&#xe616;</i> " + parentMenus.get(i).getTitle() + "<i class=\"Hui-iconfont Hui-admin-menu-dropdown-arrow\">&#xe6d5;</i></dt>");
                        html.append("<dd class=\"Hui-menu-item\">");
                        html.append("<ul>");
                        html.append(menusBuffer);
                        html.append("</ul>");
                        html.append("</dt>");
                        html.append("</dl>");
                    } else { // 没有子级菜单
                        html.append("<dl  class=\"Hui-menu\">");
                        html.append("<dd class=\"Hui-menu-item-frist\">");
                        html.append("<ul>");
                        html.append("<li><a data-href=\"" + parentMenus.get(i).getName() + "\" data-title=\"" + parentMenus.get(i).getTitle() + "\" href=\"javascript:void(0)\"><i class=\"Hui-iconfont\">&#xe616;</i>&nbsp; " + parentMenus.get(i).getTitle() + "</a></li>");
                        html.append("</ul>");
                        html.append("</dd>");
                        html.append("</dl>");
                    }
                    menusBuffer = new StringBuffer();
                }
            }
        }
        return html.toString();
    }


    /**
     * 检测用户是否注册
     * @param type
     * @param userId
     * @return
     */
    public Boolean isFound(String type, Integer userId) {
        QueryWrapper<WmAdditionAdmin> query = new QueryWrapper<WmAdditionAdmin>();
        query.eq("type", type);
        query.eq("user_id", userId);
        WmAdditionAdmin result = wmAdditionAdminDAO.selectOne(query);
        return result != null;
    }

    public WmAdditionAdmin getWmAddition(String type, Integer userId) {
        QueryWrapper<WmAdditionAdmin> query = new QueryWrapper<WmAdditionAdmin>();
        query.eq("type", type);
        query.eq("user_id", userId);
        return wmAdditionAdminDAO.selectOne(query);
    }


    /**
     * 根据代理ID查询代理用户信息
     * @param wmAdditionId
     * @return
     */
    public WmAdditionGroupAccess getWmAdditionGroupById(Integer wmAdditionId) {
        return wmAdditionGroupAccessDAO.selectById(wmAdditionId);
    }


    public Boolean updateWmAdditionGroup(WmAdditionGroupAccess wmAdditionGroupAccess) {
        int result = wmAdditionGroupAccessDAO.updateById(wmAdditionGroupAccess);
        return result > 0;
    }

    public WmAdditionAdmin getWmAdditionByUserId(Integer userId) {
        return wmAdditionAdminDAO.selectOne(new QueryWrapper<WmAdditionAdmin>().eq("user_id", userId) );
    }



}
