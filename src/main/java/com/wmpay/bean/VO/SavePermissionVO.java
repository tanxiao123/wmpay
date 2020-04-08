package com.wmpay.bean.VO;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wmpay.template.Update;

public class SavePermissionVO {
	
	@NotNull(message = "主键不可为空", groups = {Update.class})
	private Integer id;
	
	@NotBlank(message="角色名称不可为空")
	private String roleName;
	
	@NotNull(message = "角色组不可为空")
	private Integer[] groupCheck;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer[] getGroupCheck() {
		return groupCheck;
	}

	public void setGroupCheck(Integer[] groupCheck) {
		this.groupCheck = groupCheck;
	}
	
	

}
