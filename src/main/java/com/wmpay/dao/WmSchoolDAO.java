package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.SchoolVO;
import com.wmpay.bean.WmSchool;
import com.wmpay.bean.WmSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmSchoolDAO extends BaseMapper<WmSchool> {

    IPage<SchoolVO> selectParentSchool(Page<WmSchool> page);

    IPage<SchoolVO> selectPointSchool(Page<WmSchool> page);
}