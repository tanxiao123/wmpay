package com.wmpay.bean.VO;

import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AdminVO {

    @NotNull(message = "主键不可为空", groups = {Update.class})
    private Integer wmAdminId;

    @NotBlank(message="用户名不可为空")
    private String username;

    @NotBlank(message="昵称不可为空")
    private String nickname;

    @NotBlank(message="密码不可为空")
    private String password;

    @NotNull(message = "角色不可为空")
    private Integer ruleId;

    @NotBlank(message="状态不可为空")
    private  String status;

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
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
