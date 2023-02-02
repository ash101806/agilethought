package com.agilethought.springboot.vo.request;

import javax.validation.constraints.NotEmpty;
/**
 * Values class for  a add a product type
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddProductTypeRequestVO {
	@NotEmpty(message = "{validations.product-type.code}")
	private String code;
	@NotEmpty(message = "{validations.product-type.currency}")
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