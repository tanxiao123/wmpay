package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 班级Bean
 */
public class WmSquad {

    @TableId(value = "wm_squad_id")
    @NotNull(message = "班级ID不可为空",groups = {Update.class})
    private Integer wmSquadId;

    @NotBlank(message = "班级名称不可为空")
    private String name;

    @TableLogic(value = "1", delval = "9")
    @NotBlank(message = "状态不可为空")
    private String status;

    private Date createdTime;

    private Date updatedTime;

    @NotBlank(message = "班主任名称不可为空")
    private String teacherName;

    @NotBlank(message = "班主任手机号不可为空")
    private String teacherMobile;


    public Integer getWmSquadId() {
        return wmSquadId;
    }

    public void setWmSquadId(Integer wmSquadId) {
        this.wmSquadId = wmSquadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }
}
