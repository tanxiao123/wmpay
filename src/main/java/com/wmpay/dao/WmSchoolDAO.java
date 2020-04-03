package com.wmpay.dao;

import com.wmpay.bean.WmSchool;
import com.wmpay.bean.WmSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmSchoolDAO {
    long countByExample(WmSchoolExample example);

    int deleteByExample(WmSchoolExample example);

    int deleteByPrimaryKey(Integer wmSchoolId);

    int insert(WmSchool record);

    int insertSelective(WmSchool record);

    List<WmSchool> selectByExample(WmSchoolExample example);

    WmSchool selectByPrimaryKey(Integer wmSchoolId);

    int updateByExampleSelective(@Param("record") WmSchool record, @Param("example") WmSchoolExample example);

    int updateByExample(@Param("record") WmSchool record, @Param("example") WmSchoolExample example);

    int updateByPrimaryKeySelective(WmSchool record);

    int updateByPrimaryKey(WmSchool record);
}