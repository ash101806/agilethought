package com.agilethought.springboot.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "PROCUCT_TYPE_ID")
	private Long productTypeid;
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	@Column(name = "BALANCE")
	private BigDecimal balance;
	@Column(name = "STATUS")
	private Character status;
	@Column(name = "ACTIVATION_DATE")
	private LocalDateTime activationDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", updatable = false, insertable = false)
	private Account account;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductTypeid() {
		return productTypeid;
	}
	public void setProductTypeid(Long productTypeid) {
		this.productTypeid = productTypeid;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public LocalDateTime getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}
}
