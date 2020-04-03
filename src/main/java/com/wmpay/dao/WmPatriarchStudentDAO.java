package com.wmpay.dao;

import com.wmpay.bean.WmPatriarchStudent;
import com.wmpay.bean.WmPatriarchStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmPatriarchStudentDAO {
    long countByExample(WmPatriarchStudentExample example);

    int deleteByExample(WmPatriarchStudentExample example);

    int deleteByPrimaryKey(Integer wmPatriarchStudentId);

    int insert(WmPatriarchStudent record);

    int insertSelective(WmPatriarchStudent record);

    List<WmPatriarchStudent> selectByExample(WmPatriarchStudentExample example);

    WmPatriarchStudent selectByPrimaryKey(Integer wmPatriarchStudentId);

    int updateByExampleSelective(@Param("record") WmPatriarchStudent record, @Param("example") WmPatriarchStudentExample example);

    int updateByExample(@Param("record") WmPatriarchStudent record, @Param("example") WmPatriarchStudentExample example);

    int updateByPrimaryKeySelective(WmPatriarchStudent record);

    int updateByPrimaryKey(WmPatriarchStudent record);
}