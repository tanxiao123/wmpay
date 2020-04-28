package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.AO.SquadAO;
import com.wmpay.bean.VO.SquadVO;
import com.wmpay.bean.WmSquad;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WmSquadDAO extends BaseMapper<WmSquad> {


    IPage<SquadVO> selectListPage(Page<SquadVO> page,@Param("squadId")Integer squadId, @Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);

    List<SquadAO> selectListByGradeId(@RequestParam("wmGradeId") Integer wmGradeId);
}
