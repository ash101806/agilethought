package com.agilethoght.practice.billpayments.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BillPayments")
public class BillPayment {
	@Id
	@Column(name = "PaymentId", length = 36)
	private String id;
	@Column(name = "Amount")
	private BigDecimal amount;
	@Column(name = "SeriviceCode")
	private String serviceCode;
	@Column(name = "Reference")
	private String reference;
	@Column(name = "Registered")
	private LocalDateTime registered;
	@Column(name = "Status")
	private String status;
	@Column(name = "AppliedTime")
	private LocalDateTime appliedTime;
	@Column(name = "ConfirmationReference")
	private String confirmationReference;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public LocalDateTime getRegistered() {
		return registered;
	}
	public void setRegistered(LocalDateTime registered) {
		this.registered = registered;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getAppliedTime() {
		return appliedTime;
	}
	public void setAppliedTime(LocalDateTime appliedTime) {
		this.appliedTime = appliedTime;
	}
	public String getConfirmationReference() {
		return confirmationReference;
	}
	public void setConfirmationReference(String confirmationReference) {
		this.confirmationReference = confirmationReference;
	}
}