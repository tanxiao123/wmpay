package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WmSchool {

    @NotNull(message = "学校ID不可为空", groups = {Update.class})
    @TableId(value = "wm_school_id",type = IdType.AUTO)
    private Integer wmSchoolId;

    @NotNull(message = "地区ID不可为空")
    private Integer wmAreaId;

    @NotBlank(message = "学校名称不可为空")
    private String name;

    private Integer parentId;

    private String status;

    private Date createdTime;

    private Date updatedTime;

    public Integer getWmSchoolId() {
        return wmSchoolId;
    }

    public void setWmSchoolId(Integer wmSchoolId) {
        this.wmSchoolId = wmSchoolId;
    }

    public Integer getWmAreaId() {
        return wmAreaId;
    }

    public void setWmAreaId(Integer wmAreaId) {
        this.wmAreaId = wmAreaId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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