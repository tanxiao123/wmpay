package com.wmpay.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weimai.tools.ResponseBean;
import com.weimai.tools.encrypt.Des;
import com.wmpay.bean.WmAdmin;
import com.wmpay.bean.WmAuthGroup;
import com.wmpay.bean.WmAuthGroupAccess;
import com.wmpay.bean.WmAuthRule;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmAdminDAO;
import com.wmpay.dao.WmAuthGroupAccessDAO;
import com.wmpay.dao.WmAuthGroupDAO;
import com.wmpay.dao.WmAuthRuleDAO;

@Service
public class WmAdminService {
	
	// 管理员DAO
	@Autowired
	private WmAdminDAO wmAdminDAO;
	
	// Request请求
	@Autowired
	private HttpServletRequest request;
	
	// 角色组DAO
	@Autowired
	private WmAuthGroupDAO wmAuthGroupDAO;
	
	// 角色组与权限关联DAO
	@Autowired
	private WmAuthGroupAccessDAO wmAuthGroupAccessDAO;
	
	// 节点DAO
	@Autowired
	private WmAuthRuleDAO wmAuthRuleDAO;
	
	
	/**
	 * 	根据用户名查询当前用户是否存在
	 * @param username
	 * @return
	 */
	public Boolean isDefined(String username) {
		QueryWrapper<WmAdmin> queryWrapper = new QueryWrapper<WmAdmin>();
		queryWrapper.eq("username", username);
		WmAdmin dbWmAdmin = wmAdminDAO.selectOne(queryWrapper);
		return dbWmAdmin == null ? true : false;
	}
	
	/**
	 * 	根据ID查询当前用户是否存在
	 * @param wmAdminId
	 * @return
	 */
	public Boolean isDefined(Integer wmAdminId) {
		QueryWrapper<WmAdmin> queryWrapper = new QueryWrapper<WmAdmin>();
		queryWrapper.eq("wm_admin_id", wmAdminId);
		WmAdmin dbWmAdmin = wmAdminDAO.selectOne(queryWrapper);
		return dbWmAdmin == null ? true : false;
	}


	
	
	public ResponseBean login(WmAdmin wmAdmin) {
		ResponseBean response = new ResponseBean();
		QueryWrapper<WmAdmin> queryAdmin = new QueryWrapper<WmAdmin>();
		queryAdmin.eq("username", wmAdmin.getUsername());
		WmAdmin dbWmAdmin = wmAdminDAO.selectOne(queryAdmin);
		System.out.println(Des.encode("AdminOKO", wmAdmin.getPassword() ));
		if (dbWmAdmin == null) {
			response.setStatus(-1);
			response.setTipMsg("登录失败，无当前用户");
			response.setCusMsg("登录失败，无当前用户");
		}else{
			String salt = dbWmAdmin.getSalt();
			String saltPassword = Des.encode(salt, wmAdmin.getPassword() );
			if (dbWmAdmin.getPassword().equals(saltPassword)) {
				request.getSession().setAttribute(AdminCommon.USER_SESSION, dbWmAdmin);
				response.setStatus(1);
				response.setTipMsg("登录成功");
				response.setCusMsg("登录成功");
			}else {
				response.setStatus(-2);
				response.setTipMsg("登录失败，密码错误");
				response.setCusMsg("登录失败，密码错误");
			}
		}
		return response;
	}
	
	public IPage<WmAdmin> getAdminList(PageTools pageTools){
		return wmAdminDAO.selectPageAdminList(new Page<WmAdmin>(pageTools.getStart(), pageTools.getLength()));
	}
	
	/**
	 * 根据管理员ID查询所拥有菜单
	 * @param adminId
	 * @return
	 */
	public String getMenuHTML(Integer adminId) {
		StringBuffer html = new StringBuffer();
		StringBuffer menusBuffer = new StringBuffer();
		
		// 查询当前用户下所对应得角色组
		WmAuthGroupAccess wmAuthGroupAccess = wmAuthGroupAccessDAO.selectOne(new QueryWrapper<WmAuthGroupAccess>().eq("wm_admin_id", adminId));
		if (wmAuthGroupAccess != null) {
			WmAuthGroup wmAuthGroup = wmAuthGroupDAO.selectOne(new QueryWrapper<WmAuthGroup>().eq("wm_auth_group_id", wmAuthGroupAccess.getWmAuthGroupId()) );
			if (wmAuthGroup != null) {
				// 取出该管理员所拥有的菜单 并转为Integer
				String[] rule = wmAuthGroup.getRules().split("\\|");
				Integer[] ruleId = new Integer[rule.length];
				for (int i = 0; i<rule.length; i++) {
					ruleId[i] = Integer.parseInt(rule[i]);
				}
				List<WmAuthRule> parentMenus = wmAuthRuleDAO.getParentMenus(ruleId);
				List<WmAuthRule> menus = wmAuthRuleDAO.getNotParentMenus(ruleId);
				
				for(int i = 0; i<parentMenus.size(); i++ ) {
					int index = 0; // 标识为如果有下级就+1
					for (int j = 0; j < menus.size(); j++) {
						if (menus.get(j).getParentId().equals(parentMenus.get(i).getWmAuthRuleId())) {
							menusBuffer.append("<li><a data-href=\""+menus.get(j).getName()+"\" data-title=\""+menus.get(j).getTitle()+"\" href=\"javascript:void(0)\">"+menus.get(j).getTitle()+"</a></li>");
							index ++;
						}
						
					}
					
					if (index > 0) {
						html.append("<dl  class=\"Hui-menu\">");
						html.append("<dt class=\"Hui-menu-title\"><i class=\"Hui-iconfont\">&#xe616;</i> "+parentMenus.get(i).getTitle()+"<i class=\"Hui-iconfont Hui-admin-menu-dropdown-arrow\">&#xe6d5;</i></dt>");
						html.append("<dd class=\"Hui-menu-item\">");
						html.append("<ul>");
						html.append(menusBuffer);
						html.append("</ul>");
						html.append("</dt>");
						html.append("</dl>");
					}else { // 没有子级菜单
						html.append("<dl  class=\"Hui-menu\">");
						html.append("<dd class=\"Hui-menu-item-frist\">");
						html.append("<ul>");
						html.append("<li><a data-href=\""+parentMenus.get(i).getName()+"\" data-title=\""+parentMenus.get(i).getTitle()+"\" href=\"javascript:void(0)\"><i class=\"Hui-iconfont\">&#xe616;</i>&nbsp; "+parentMenus.get(i).getTitle()+"</a></li>");
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
	
	
	public Boolean editAdmin(WmAdmin wmAdmin) {
		return wmAdminDAO.updateById(wmAdmin) > 0;
	}
	
	
	
	
}
