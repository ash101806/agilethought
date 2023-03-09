package com.agilethought.springboot.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * Entity class to manage PRODUCT table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "Product")
public class Product {
	@Id
	@Column(name = "Id",columnDefinition="uniqueidentifier")
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	@Column(name = "ProductTypeId",columnDefinition="uniqueidentifier")
	private String productTypeId;
	@Column(name = "AccountId",columnDefinition="uniqueidentifier")
	private String accountId;
	@Column(name = "Balance")
	private BigDecimal balance;
	@Column(name = "Status")
	private Character status;
	@Column(name = "ActivationDate")
	private LocalDateTime activationDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AccountId", updatable = false, insertable = false)
	private Account account;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductTypeId", updatable = false, insertable = false)
	private ProductType productType;
	

	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
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
