package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmCommodity;
import org.apache.ibatis.annotations.Param;

public interface WmCommodityDAO extends BaseMapper<WmCommodity> {

    IPage<WmCommodity> selectPageCommodity(Page<WmCommodity> page, @Param("userId") Integer userId, @Param("type")String type);
}
