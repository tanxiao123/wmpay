package com.wmpay.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wmpay.template.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WmGrade {

    @NotNull(message = "班级ID不可为空", groups = {Update.class})
    @TableId(value = "wm_grade_id", type = IdType.AUTO)
    private Integer wmGradeId;

    @NotNull(message = "学校ID不可为空")
    private Integer wmSchoolId;

    @NotNull(message = "班级名称不可为空")
    private String name;

    @TableLogic(value = "1", delval = "9")
    private String status;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date createdTime;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date updatedTime;

    public Integer getWmGradeId() {
        return wmGradeId;
    }

    public void setWmGradeId(Integer wmGradeId) {
        this.wmGradeId = wmGradeId;
    }

    public Integer getWmSchoolId() {
        return wmSchoolId;
    }

    public void setWmSchoolId(Integer wmSchoolId) {
        this.wmSchoolId = wmSchoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}