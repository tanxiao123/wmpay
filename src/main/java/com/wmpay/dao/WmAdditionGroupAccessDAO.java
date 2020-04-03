package com.wmpay.dao;

import com.wmpay.bean.WmAdditionGroupAccess;
import com.wmpay.bean.WmAdditionGroupAccessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmAdditionGroupAccessDAO {
    long countByExample(WmAdditionGroupAccessExample example);

    int deleteByExample(WmAdditionGroupAccessExample example);

    int insert(WmAdditionGroupAccess record);

    int insertSelective(WmAdditionGroupAccess record);

    List<WmAdditionGroupAccess> selectByExample(WmAdditionGroupAccessExample example);

    int updateByExampleSelective(@Param("record") WmAdditionGroupAccess record, @Param("example") WmAdditionGroupAccessExample example);

    int updateByExample(@Param("record") WmAdditionGroupAccess record, @Param("example") WmAdditionGroupAccessExample example);
}