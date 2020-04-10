package com.wmpay.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;

public class WmAuthRule {
	
    private Integer wmAuthRuleId;

    private String type;

    private Integer parentId;

    private String name;

    private String title;

    private String icon;

    private String remark;

    private String ismenu;

    private Date createdTime;

    private Date updatedTime;

    private Integer weigh;

    @TableLogic(value="1",delval="9")
    private String status;


    public Integer getWmAuthRuleId() {
		return wmAuthRuleId;
	}

	public void setWmAuthRuleId(Integer wmAuthRuleId) {
		this.wmAuthRuleId = wmAuthRuleId;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(String ismenu) {
        this.ismenu = ismenu == null ? null : ismenu.trim();
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

    public Integer getWeigh() {
        return weigh;
    }

    public void setWeigh(Integer weigh) {
        this.weigh = weigh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}