package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmpay.bean.WmGradeSquad;
import com.wmpay.dao.WmGradeSquadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmGradeSquadService {

    @Autowired
    WmGradeSquadDAO wmGradeSquadDAO;

    public WmGradeSquad selectBySquadId(Integer wmSquadId) {
        return wmGradeSquadDAO.selectOne(new QueryWrapper<WmGradeSquad>().eq("wm_squad_id", wmSquadId));
    }

}
