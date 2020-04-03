package com.wmpay.dao;

import com.wmpay.bean.WmOrder;
import com.wmpay.bean.WmOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmOrderDAO {
    long countByExample(WmOrderExample example);

    int deleteByExample(WmOrderExample example);

    int deleteByPrimaryKey(Integer wmOrderId);

    int insert(WmOrder record);

    int insertSelective(WmOrder record);

    List<WmOrder> selectByExample(WmOrderExample example);

    WmOrder selectByPrimaryKey(Integer wmOrderId);

    int updateByExampleSelective(@Param("record") WmOrder record, @Param("example") WmOrderExample example);

    int updateByExample(@Param("record") WmOrder record, @Param("example") WmOrderExample example);

    int updateByPrimaryKeySelective(WmOrder record);

    int updateByPrimaryKey(WmOrder record);
}