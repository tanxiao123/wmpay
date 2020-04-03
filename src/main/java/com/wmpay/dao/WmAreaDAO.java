package com.wmpay.dao;

import com.wmpay.bean.WmArea;
import com.wmpay.bean.WmAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmAreaDAO {
    long countByExample(WmAreaExample example);

    int deleteByExample(WmAreaExample example);

    int deleteByPrimaryKey(Integer wmAreaId);

    int insert(WmArea record);

    int insertSelective(WmArea record);

    List<WmArea> selectByExample(WmAreaExample example);

    WmArea selectByPrimaryKey(Integer wmAreaId);

    int updateByExampleSelective(@Param("record") WmArea record, @Param("example") WmAreaExample example);

    int updateByExample(@Param("record") WmArea record, @Param("example") WmAreaExample example);

    int updateByPrimaryKeySelective(WmArea record);

    int updateByPrimaryKey(WmArea record);
}