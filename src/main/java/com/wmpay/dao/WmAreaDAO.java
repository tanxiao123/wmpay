package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmArea;

import java.util.List;

public interface WmAreaDAO extends BaseMapper<WmArea> {
    IPage<WmArea> selectAreaPage(Page<WmArea> page);

    List<WmArea> selectAreaPage();
}