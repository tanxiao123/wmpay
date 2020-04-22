package com.wmpay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.SquadVO;
import com.wmpay.bean.WmSquad;

public interface WmSquadDAO extends BaseMapper<WmSquad> {


    IPage<SquadVO> selectListPage(Page<SquadVO> page);
}
