package com.wmpay.dao;

import com.wmpay.bean.WmOrderType;
import com.wmpay.bean.WmOrderTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmOrderTypeDAO {
    long countByExample(WmOrderTypeExample example);

    int deleteByExample(WmOrderTypeExample example);

    int deleteByPrimaryKey(Integer wmOrderTypeId);

    int insert(WmOrderType record);

    int insertSelective(WmOrderType record);

    List<WmOrderType> selectByExample(WmOrderTypeExample example);

    WmOrderType selectByPrimaryKey(Integer wmOrderTypeId);

    int updateByExampleSelective(@Param("record") WmOrderType record, @Param("example") WmOrderTypeExample example);

    int updateByExample(@Param("record") WmOrderType record, @Param("example") WmOrderTypeExample example);

    int updateByPrimaryKeySelective(WmOrderType record);

    int updateByPrimaryKey(WmOrderType record);
}