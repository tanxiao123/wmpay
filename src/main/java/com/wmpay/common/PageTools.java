package com.wmpay.common;


import javax.validation.constraints.NotNull;

public class PageTools {
	
	@NotNull(message = "当前页数不可为空")
	private Integer start;
	
	@NotNull(message = "每页显示条数不可为空")
	private Integer length;
	


	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	

}
