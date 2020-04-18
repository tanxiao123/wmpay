package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmOrder;
import com.wmpay.bean.WmOrderExample;
import java.util.List;

import com.wmpay.common.PageTools;
import org.apache.ibatis.annotations.Param;

public interface WmOrderDAO extends BaseMapper<WmOrder> {

    IPage<WmOrder> selectPageList(Page<WmOrder> page);
}