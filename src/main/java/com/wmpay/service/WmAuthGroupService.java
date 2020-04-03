package com.wmpay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmAuthGroup;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmAuthGroupDAO;

@Service
public class WmAuthGroupService {
	
	@Autowired
	private WmAuthGroupDAO wmAuthGroupDAO;
	
	public IPage<WmAuthGroup> getAuthGroupList(PageTools pageTools) {
		return wmAuthGroupDAO.selectPageAuthGroup(new Page<WmAuthGroup>(pageTools.getPage(), pageTools.getLimit()) );
	}

}
