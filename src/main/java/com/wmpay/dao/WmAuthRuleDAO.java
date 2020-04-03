package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmpay.bean.WmAuthRule;
import com.wmpay.bean.WmAuthRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmAuthRuleDAO extends BaseMapper<WmAuthRule> {
	
	public List<WmAuthRule> getParentMenus(@Param("ids") Integer[] ids);
	
	public List<WmAuthRule> getNotParentMenus(@Param("ids") Integer[] ids);
	
	public List<WmAuthRule> getMenusByIds(@Param("ids") Integer[] ids);

}