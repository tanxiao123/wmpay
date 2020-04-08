package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmAdmin;

public interface WmAdminDAO extends BaseMapper<WmAdmin> {
	
	IPage<WmAdmin> selectPageAdminList(Page<WmAdmin> page);

}