package com.wmpay.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmAdmin {
	
	@TableId(value="wm_admin_id", type = IdType.AUTO)
    private Integer wmAdminId;

	@NotNull
	@Size(min = 2, max = 50, message="用户名最小不可低于两个字符，最大不可超过50字符")
    private String username;

    private String nickname;

    @NotNull
    @Size(min = 6, max = 18, message = "密码最小不可低于6个字符，最大不可超过18字符")
    private String password;

    private String salt;

    private String avatar;

    private Date logintime;

    private String loginip;

    private Date createdTime;

    private Date updatedTime;

    private String status;

    
    
    public Integer getWmAdminId() {
		return wmAdminId;
	}

	public void setWmAdminId(Integer wmAdminId) {
		this.wmAdminId = wmAdminId;
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
}