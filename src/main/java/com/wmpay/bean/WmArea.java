package com.wmpay.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WmArea {

    @NotNull(message = "地区ID不可为空", groups = {Update.class})
    @TableId(value = "wm_area_id", type = IdType.AUTO)
    private Integer wmAreaId;

    @NotBlank(message = "地区昵称不可为空")
    private String name;

    @NotBlank(message = "地区Code不可为空")
    private String code;

    private Integer parentId;

    @NotNull(message = "等级不可为空")
    private Integer level;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date createdTime;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date updatedTime;

    @TableLogic(value = "1", delval = "9")
    private String status;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}