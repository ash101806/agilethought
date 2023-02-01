package com.agilethought.springboot.vo.request;

public class UpdateProductTypeRequestVO {
	private String code;
	private String currency;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}