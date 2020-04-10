package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmArea;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmAreaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
