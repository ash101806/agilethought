package com.agilethought.springboot.vo.response;

import java.util.List;

import com.agilethought.springboot.vo.ProductVO;
/**
 * Values class for show information of an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class FindAccountResponseVO {
	private Long id;
	private String accountNumber;
	private String mainProductType;
	private Long mainProductId;
	private List<ProductVO> products;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getMainProductType() {
		return mainProductType;
	}
	public void setMainProductType(String mainProductType) {
		this.mainProductType = mainProductType;
	}
	public Long getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(Long mainProductId) {
		this.mainProductId = mainProductId;
	}
	public List<ProductVO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}
}
