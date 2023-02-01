package com.agilethought.springboot.vo.response;

import java.math.BigDecimal;

public class AddAccountRequestVO {
	
	private Long clientId;
	private String accounTypeCode;
	private BigDecimal initialBalance;
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getAccounTypeCode() {
		return accounTypeCode;
	}
	public void setAccounTypeCode(String accounTypeCode) {
		this.accounTypeCode = accounTypeCode;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
}
