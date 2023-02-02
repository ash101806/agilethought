package com.agilethought.springboot.vo;
/**
 * Values class for a Product Type
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class ProductTypeVO {
	private Long id;
	private String code;
	private String currency;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
