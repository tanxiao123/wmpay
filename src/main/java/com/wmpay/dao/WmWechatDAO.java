package com.wmpay.dao;

import com.wmpay.bean.WmWechat;
import com.wmpay.bean.WmWechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmWechatDAO {
    long countByExample(WmWechatExample example);

    int deleteByExample(WmWechatExample example);

    int deleteByPrimaryKey(Integer wmWechatId);

    int insert(WmWechat record);

    int insertSelective(WmWechat record);

    List<WmWechat> selectByExample(WmWechatExample example);

    WmWechat selectByPrimaryKey(Integer wmWechatId);

    int updateByExampleSelective(@Param("record") WmWechat record, @Param("example") WmWechatExample example);

    int updateByExample(@Param("record") WmWechat record, @Param("example") WmWechatExample example);

    int updateByPrimaryKeySelective(WmWechat record);

    int updateByPrimaryKey(WmWechat record);
}