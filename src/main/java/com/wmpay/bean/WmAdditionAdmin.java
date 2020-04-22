package com.wmpay.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class WmAdditionAdmin {

    @TableId(value = "wm_addition_admin_id", type = IdType.AUTO)
    private Integer wmAdditionAdminId;

    private Integer wmThirdPayConfigId;

    private Integer wmWechatPayConfigId;

    @NotBlank(message = "用户名不可为空")
    private String username;

    @NotBlank(message = "昵称不可为空")
    private String nickname;

    @NotBlank(message = "密码不可为空")
    private String password;

    private String salt;

    private String avatar;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date logintime;

    private String loginip;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date createdTime;

    @JSONField(name = "resType", format = "yyyy-MM-dd")
    private Date updatedTime;

    @TableLogic(value = "1", delval = "9")
    private String status;

    @NotBlank(message = "用户类型不可为空")
    private String type;

    private String isDefaultPay;

    public Integer getWmAdditionAdminId() {
        return wmAdditionAdminId;
    }

    public void setWmAdditionAdminId(Integer wmAdditionAdminId) {
        this.wmAdditionAdminId = wmAdditionAdminId;
    }

    public Integer getWmThirdPayConfigId() {
        return wmThirdPayConfigId;
    }

    public void setWmThirdPayConfigId(Integer wmThirdPayConfigId) {
        this.wmThirdPayConfigId = wmThirdPayConfigId;
    }

    public Integer getWmWechatPayConfigId() {
        return wmWechatPayConfigId;
    }

    public void setWmWechatPayConfigId(Integer wmWechatPayConfigId) {
        this.wmWechatPayConfigId = wmWechatPayConfigId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsDefaultPay() {
        return isDefaultPay;
    }

    public void setIsDefaultPay(String isDefaultPay) {
        this.isDefaultPay = isDefaultPay;
    }
}