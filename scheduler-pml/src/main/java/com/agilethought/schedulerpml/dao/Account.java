package com.agilethought.schedulerpml.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity Class for ACCOUNT Table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@Column(name = "ID", length = 17)
	private String id;
	@Column(name = "ZIP_CODE", length = 13)
	private String zipCode;
	@Column(name = "STATE", length = 40)
	private String state;
	@Column(name = "COUNTRY", length = 2)
	private String country;
	@Column(name = "ACCOUNT_AGE")
	private Integer accountAge;
	@Column(name = "USER_REGISTERED")
	private Boolean registered;
	@Column(name = "PAYMENT_INSTRUMENT_AGE", length = 20)
	private String paymentInstrumentAgeInAccount ;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getAccountAge() {
		return accountAge;
	}
	public void setAccountAge(Integer accountAge) {
		this.accountAge = accountAge;
	}
	public Boolean getRegistered() {
		return registered;
	}
	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}
	public String getPaymentInstrumentAgeInAccount() {
		return paymentInstrumentAgeInAccount;
	}
	public void setPaymentInstrumentAgeInAccount(String paymentInstrumentAgeInAccount) {
		this.paymentInstrumentAgeInAccount = paymentInstrumentAgeInAccount;
	}
}
