package com.agilethought.springboot.vo.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * Values class for a add an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddAccountRequestVO {
	@NotNull(message = "{validations.accounts.clientid.not-null}")
	private String clientId;
	@NotEmpty(message = "{validations.accounts.accountTypeCode.not-null}")
	private String accountTypeCode;
	@NotNull(message = "{validations.accounts.accountTypeCode.not-null}")
	@DecimalMin(value = "0.00")
	private BigDecimal initialBalance;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
}
