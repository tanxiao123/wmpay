package com.wmpay.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WmCommodity {

    @NotNull(message = "商品主键不可为空", groups = {Update.class})
    @TableId(value = "wm_commodity_id", type = IdType.AUTO)
    private Integer wmCommodityId;

    private Integer userId;

    private String type;

    @NotBlank(message = "标题不可为空")
    private String title;

    @NotBlank(message = "商品描述不可为空")
    private String description;

    @NotNull(message = "商品金额不可为空")
    private Integer total;

    @TableLogic(value = "1", delval = "9")
    private String status;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date createdTime;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date updatedTime;

    public Integer getWmCommodityId() {
        return wmCommodityId;
    }

    public void setWmCommodityId(Integer wmCommodityId) {
        this.wmCommodityId = wmCommodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
