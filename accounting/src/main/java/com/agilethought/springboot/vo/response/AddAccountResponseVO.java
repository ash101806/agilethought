package com.agilethought.springboot.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Values class for reponse to a add an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddAccountResponseVO {
	private String accountNumber;
	private Character status;
	private Long productId;
	@JsonProperty("contractNumber")
	private String contractUUID;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getContractUUID() {
		return contractUUID;
	}
	public void setContractUUID(String contractUUID) {
		this.contractUUID = contractUUID;
	}
	
}
