package com.wmpay.dao;

import com.wmpay.bean.WmConfig;
import com.wmpay.bean.WmConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmConfigDAO {
    long countByExample(WmConfigExample example);

    int deleteByExample(WmConfigExample example);

    int deleteByPrimaryKey(Integer wmConfigId);

    int insert(WmConfig record);

    int insertSelective(WmConfig record);

    List<WmConfig> selectByExample(WmConfigExample example);

    WmConfig selectByPrimaryKey(Integer wmConfigId);

    int updateByExampleSelective(@Param("record") WmConfig record, @Param("example") WmConfigExample example);

    int updateByExample(@Param("record") WmConfig record, @Param("example") WmConfigExample example);

    int updateByPrimaryKeySelective(WmConfig record);

    int updateByPrimaryKey(WmConfig record);
}