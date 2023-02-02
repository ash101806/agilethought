package com.agilethought.springboot.vo;

import java.math.BigDecimal;
/**
 * Values class for a product information
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class ProductVO {
	private Long id;
	private String type;
	private String currency;
	private BigDecimal balance;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
