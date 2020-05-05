package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmpay.bean.WmConfig;
import com.wmpay.bean.WmConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmConfigDAO extends BaseMapper<WmConfig> {
    String selectValue(@Param("name") String name);
}