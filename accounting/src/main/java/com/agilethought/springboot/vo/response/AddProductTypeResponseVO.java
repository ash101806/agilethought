package com.agilethought.springboot.vo.response;
/**
 * Values class for response to a add a product type
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddProductTypeResponseVO {
	private String id;
	private String code;
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
}
