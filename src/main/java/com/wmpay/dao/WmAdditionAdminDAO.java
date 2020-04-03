package com.wmpay.dao;

import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmAdditionAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmAdditionAdminDAO {
	long countByExample(WmAdditionAdminExample example);

	int deleteByExample(WmAdditionAdminExample example);

	int deleteByPrimaryKey(Integer wmAdditionAdminId);

	int insert(WmAdditionAdmin record);

	int insertSelective(WmAdditionAdmin record);

	List<WmAdditionAdmin> selectByExample(WmAdditionAdminExample example);

	WmAdditionAdmin selectByPrimaryKey(Integer wmAdditionAdminId);

	int updateByExampleSelective(@Param("record") WmAdditionAdmin record,
			@Param("example") WmAdditionAdminExample example);

	int updateByExample(@Param("record") WmAdditionAdmin record, @Param("example") WmAdditionAdminExample example);

	int updateByPrimaryKeySelective(WmAdditionAdmin record);

	int updateByPrimaryKey(WmAdditionAdmin record);
}