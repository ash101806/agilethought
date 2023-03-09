package com.agilethought.springboot.vo;
/**
 * Values class for a Product Type
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class ProductTypeVO {
	private String id;
	private String code;
	private String currency;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
