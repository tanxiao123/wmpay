package com.wmpay.template;

public enum ResponseEnum {
	SUCCESS(1,"操作成功"),
	ERROR(-1,"操作失败,请稍后重试"),
	NOT_PERMISSION(-2, "无权限访问"),
	FIELD_ERROR(-3,"字段信息错误"),
	PERMISSION_REPEAT(-10,"当前角色组正在使用，请先删除关联");
	
	public Integer status;
	
	public String msg;
	
	
	private ResponseEnum(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	
}
