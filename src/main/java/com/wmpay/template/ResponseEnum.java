package com.wmpay.template;

public enum ResponseEnum {
	SUCCESS(1,"操作成功"),
	SUCCESS_FOUND(2,"用户信息已注册"),
	SUCCESS_NOT_FOUND_ADD(3,"当前用户信息未注册，有权限 可添加"),
	SUCCESS_PAY_NOT_FOUND_ADMIN(4, "当前用户信息暂未开通支付，是否开通？"),
	SUCCESS_PAY(5,"用户支付配置已存在"),
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
