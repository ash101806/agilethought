package com.agilethought.schedulerpml.dto;

import java.math.BigDecimal;
/**
 * DTO for whatsapp send message request
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class RiskOperationsIPDTO {
	private String ip;
	private Long noOperations;
	private BigDecimal amount;
	
	
	public RiskOperationsIPDTO(String ip, Long noOperations, BigDecimal amount) {
		super();
		this.ip = ip;
		this.noOperations = noOperations;
		this.amount = amount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Long getNoOperations() {
		return noOperations;
	}
	public void setNoOperations(Long noOperations) {
		this.noOperations = noOperations;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
