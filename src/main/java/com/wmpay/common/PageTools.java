package com.wmpay.common;

import com.sun.istack.internal.NotNull;

public class PageTools {
	
	@NotNull
	private Integer start;
	
	@NotNull
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
