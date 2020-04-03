package com.wmpay.dao;

import com.wmpay.bean.WmStudent;
import com.wmpay.bean.WmStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmStudentDAO {
    long countByExample(WmStudentExample example);

    int deleteByExample(WmStudentExample example);

    int deleteByPrimaryKey(Integer wmStudentId);

    int insert(WmStudent record);

    int insertSelective(WmStudent record);

    List<WmStudent> selectByExample(WmStudentExample example);

    WmStudent selectByPrimaryKey(Integer wmStudentId);

    int updateByExampleSelective(@Param("record") WmStudent record, @Param("example") WmStudentExample example);

    int updateByExample(@Param("record") WmStudent record, @Param("example") WmStudentExample example);

    int updateByPrimaryKeySelective(WmStudent record);

    int updateByPrimaryKey(WmStudent record);
}