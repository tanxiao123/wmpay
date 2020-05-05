package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmPatriarchStudent {
    @TableId(value = "wm_patriarch_student_id", type = IdType.AUTO)
    private Integer wmPatriarchStudentId;

    private Integer wmPatriarchId;

    private Integer wmStudentId;

    public Integer getWmPatriarchStudentId() {
        return wmPatriarchStudentId;
    }

    public void setWmPatriarchStudentId(Integer wmPatriarchStudentId) {
        this.wmPatriarchStudentId = wmPatriarchStudentId;
    }

    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }

    public Integer getWmStudentId() {
        return wmStudentId;
    }

    public void setWmStudentId(Integer wmStudentId) {
        this.wmStudentId = wmStudentId;
    }
}