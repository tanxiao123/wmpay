package com.wmpay.util;

public class DataTableResponse {
	
	private Integer status;
	
	private String cusMsg;
	
	private String tipMsg;
	
	private Object data;
	
	private long recordsTotal;
	
	private long recordsFiltered;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCusMsg() {
		return cusMsg;
	}

	public void setCusMsg(String cusMsg) {
		this.cusMsg = cusMsg;
	}

	public String getTipMsg() {
		return tipMsg;
	}

	public void setTipMsg(String tipMsg) {
		this.tipMsg = tipMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	

}
