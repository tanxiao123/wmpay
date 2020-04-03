package com.wmpay.common;

import org.hibernate.validator.constraints.NotBlank;

import com.sun.istack.internal.NotNull;

public class PageTools {
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer limit;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	

}
