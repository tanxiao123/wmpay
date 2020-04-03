package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmAuthGroup;

/**
 * 分组
 * @author 谭潇
 *
 */
public interface WmAuthGroupDAO extends BaseMapper<WmAuthGroup> {

	IPage<WmAuthGroup> selectPageAuthGroup(Page<WmAuthGroup> page);

}