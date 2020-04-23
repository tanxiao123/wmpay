package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.AO.CakeStatisticsAO;
import com.wmpay.bean.AO.DayNumberStatisticsAO;
import com.wmpay.bean.VO.OrderVO;
import com.wmpay.bean.WmOrder;
import com.wmpay.bean.WmOrderExample;

import java.util.HashMap;
import java.util.List;

import com.wmpay.common.PageTools;
import org.apache.ibatis.annotations.Param;

public interface WmOrderDAO extends BaseMapper<WmOrder> {

    IPage<OrderVO> selectPageList(Page<WmOrder> page);

    DayNumberStatisticsAO getDayNumberStatistics(@Param("mathTime")String mathTime, @Param("days")Integer days);

    CakeStatisticsAO getStatisticsCake(@Param("days")String days);
}