package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmArea;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmAreaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WmAreaService {

    @Autowired
    private WmAreaDAO wmAreaDAO;

    /**
     * 分页获取地区信息
     * @param pageTools
     * @return
     */
    public IPage<WmArea> selectListPage(PageTools pageTools) {
        return wmAreaDAO.selectAreaPage(new Page<WmArea>(pageTools.getStart(), pageTools.getLength() ));
    }

    public WmArea getWmAreaById(Integer wmAreaId) {
        return wmAreaDAO.selectById(wmAreaId);
    }

    /**
     * 修改地区
     * @param wmArea
     * @return
     */
    public Boolean updateWmArea(WmArea wmArea) {
        wmArea.setUpdatedTime(new Date());
        int result = wmAreaDAO.updateById(wmArea);
        return result > 0;
    }

    /**
     * 添加地区
     * @param wmArea
     * @return
     */
    public Boolean addWmArea(WmArea wmArea) {
        wmArea.setUpdatedTime(new Date());
        wmArea.setCreatedTime(new Date());
        int result = wmAreaDAO.insert(wmArea);
        return result > 0;
    }

    /**
     * 删除地区
     * @param wmAreaId
     * @return
     */
    public Boolean delWmArea(Integer wmAreaId) {
        int result = wmAreaDAO.deleteById(wmAreaId);
        return result > 0;
    }


    public List<WmArea> selectListNoPage() {
        return wmAreaDAO.selectAreaPage();
    }

}
