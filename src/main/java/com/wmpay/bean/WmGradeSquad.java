package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmGradeSquad {

    @TableId(value = "wm_grade_squad_id", type = IdType.AUTO)
    private Integer wmGradeSquadId;

    private Integer wmSquadId;

    private Integer wmGradeId;

    public Integer getWmGradeSquadId() {
        return wmGradeSquadId;
    }

    public void setWmGradeSquadId(Integer wmGradeSquadId) {
        this.wmGradeSquadId = wmGradeSquadId;
    }

    public Integer getWmSquadId() {
        return wmSquadId;
    }

    public void setWmSquadId(Integer wmSquadId) {
        this.wmSquadId = wmSquadId;
    }

    public Integer getWmGradeId() {
        return wmGradeId;
    }

    public void setWmGradeId(Integer wmGradeId) {
        this.wmGradeId = wmGradeId;
    }
}
