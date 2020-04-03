package com.wmpay.dao;

import com.wmpay.bean.WmGrade;
import com.wmpay.bean.WmGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmGradeDAO {
    long countByExample(WmGradeExample example);

    int deleteByExample(WmGradeExample example);

    int deleteByPrimaryKey(Integer wmGradeId);

    int insert(WmGrade record);

    int insertSelective(WmGrade record);

    List<WmGrade> selectByExample(WmGradeExample example);

    WmGrade selectByPrimaryKey(Integer wmGradeId);

    int updateByExampleSelective(@Param("record") WmGrade record, @Param("example") WmGradeExample example);

    int updateByExample(@Param("record") WmGrade record, @Param("example") WmGradeExample example);

    int updateByPrimaryKeySelective(WmGrade record);

    int updateByPrimaryKey(WmGrade record);
}