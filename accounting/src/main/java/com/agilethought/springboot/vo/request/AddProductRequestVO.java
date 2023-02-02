package com.agilethought.springboot.vo.request;

import java.math.BigDecimal;

/**
 * Values Class for add product to an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddProductRequestVO {
	private String accountNumber;
	private String productTypeCode;
	private BigDecimal initialBalance;
	private Long clientId;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
}
