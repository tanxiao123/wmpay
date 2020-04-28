package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.AO.SchoolAO;
import com.wmpay.bean.VO.SchoolVO;
import com.wmpay.bean.WmSchool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmSchoolDAO extends BaseMapper<WmSchool> {

    IPage<SchoolVO> selectParentSchool(Page<WmSchool> page, @Param("schoolId")Integer schoolId);

    IPage<SchoolVO> selectPointSchool(Page<WmSchool> page, @Param("wmSchoolId")Integer wmSchoolId);

    /**
     * 不分页 分校区PID查询列表数据
     * @return
     */
    List<WmSchool> selectSchoolList(@Param("wmSchoolId")Integer wmSchoolId);

    List<SchoolAO> selectSchoolListApi(@Param("wmAreaId")Integer wmAreaId);
}