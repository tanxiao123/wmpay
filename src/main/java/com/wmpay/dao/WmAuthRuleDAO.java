package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmpay.bean.WmAuthRule;
import com.wmpay.bean.WmAuthRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmAuthRuleDAO extends BaseMapper<WmAuthRule> {
	
	// 查询父级的菜单
	public List<WmAuthRule> getParentMenus(@Param("ids") Integer[] ids);
	
	// 查询不为父级的菜单
	public List<WmAuthRule> getNotParentMenus(@Param("ids") Integer[] ids);
	
	// 根据IDS查询菜单
	public List<WmAuthRule> getMenusByIds(@Param("ids") Integer[] ids);
	
	// 根据IDS查询功能路由
	public List<WmAuthRule> getFunctionMenusInIds();
	
	// 获取所有的菜单
	public String getAllMenu();
	
	// 获取所有的功能路由
	public String getAllFunction();

}