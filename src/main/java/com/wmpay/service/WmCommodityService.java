package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmCommodity;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmCommodityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class WmCommodityService {

    @Autowired
    private WmCommodityDAO wmCommodityDAO;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取商品列表
     * @param pageTools
     * @param userId
     * @param type
     * @return
     */
    public IPage<WmCommodity> getWmCommodityList(PageTools pageTools, Integer userId, String type) {
        return wmCommodityDAO.selectPageCommodity(new Page<WmCommodity>(pageTools.getStart(), pageTools.getLength()), userId, type);
    }

    /**
     * 添加商品信息
     * @param wmCommodity
     * @return
     */
    public Boolean addWmCommodity(WmCommodity wmCommodity) {
        Date date = new Date();
        // 此处只查询自己添加得商品信息
        WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
        wmCommodity.setUpdatedTime(date);
        wmCommodity.setCreatedTime(date);
        wmCommodity.setUserId(admin.getUserId() );
        wmCommodity.setStatus("1");
        wmCommodity.setType(admin.getType() );
        int result = wmCommodityDAO.insert(wmCommodity);
        return result > 0;
    }

    /**
     * 删除商品信息
     * @param wmCommodityId
     * @return
     */
    public Boolean delWmCommodity(Integer wmCommodityId) {
        int result = wmCommodityDAO.deleteById(wmCommodityId);
        return result > 0;
    }

    /**
     * 根据主键查找商品信息
     * @param wmCommodityId
     * @return
     */
    public WmCommodity getCommodityById(Integer wmCommodityId) {
        return wmCommodityDAO.selectById(wmCommodityId);
    }

    public Boolean updateCommodity(WmCommodity wmCommodity) {
        int result = wmCommodityDAO.updateById(wmCommodity);
        return result > 0;
    }
}
