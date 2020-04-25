package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.GradeVO;
import com.wmpay.bean.WmGrade;
import com.wmpay.bean.WmGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WmGradeDAO extends BaseMapper<WmGrade> {

    // 查询年级信息 分权限查看
    IPage<GradeVO> selectGradeList(Page<WmGrade> page, @Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);
}