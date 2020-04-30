package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmpay.bean.AO.WmPatriarchAO;
import com.wmpay.bean.WmPatriarch;
import com.wmpay.bean.WmPatriarchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmPatriarchDAO extends BaseMapper<WmPatriarch> {
    WmPatriarchAO selectPatriarchInfoByOpenId(@Param("openid") String openid);
}