package com.agilethought.springboot.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Entity class to manage PRODUCT table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_product", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
	private Long id;
	@Column(name = "PROCUCT_TYPE_ID")
	private Long productTypeId;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROCUCT_TYPE_ID", updatable = false, insertable = false)
	private ProductType productType;
	

	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
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
