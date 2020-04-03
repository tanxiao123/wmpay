package com.wmpay.dao;

import com.wmpay.bean.WmPatriarch;
import com.wmpay.bean.WmPatriarchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmPatriarchDAO {
    long countByExample(WmPatriarchExample example);

    int deleteByExample(WmPatriarchExample example);

    int deleteByPrimaryKey(Integer wmPatriarchId);

    int insert(WmPatriarch record);

    int insertSelective(WmPatriarch record);

    List<WmPatriarch> selectByExample(WmPatriarchExample example);

    WmPatriarch selectByPrimaryKey(Integer wmPatriarchId);

    int updateByExampleSelective(@Param("record") WmPatriarch record, @Param("example") WmPatriarchExample example);

    int updateByExample(@Param("record") WmPatriarch record, @Param("example") WmPatriarchExample example);

    int updateByPrimaryKeySelective(WmPatriarch record);

    int updateByPrimaryKey(WmPatriarch record);
}