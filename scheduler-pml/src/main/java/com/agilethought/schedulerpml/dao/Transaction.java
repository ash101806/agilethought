package com.agilethought.schedulerpml.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	@Id
	@Column(name = "ID", length = 36)
	private String id;
	@Column(name = "ACCOUNT_ID", length = 17)
	private String accountId;
	@Column(name = "AMOUNT", precision = 12,scale =4 )
	private BigDecimal amount;
	@Column(name = "CURRENCY_CODE", length = 3)
	private String currencyCode;
	@Column(name = "LOCAL_HOUR")
	private Integer localHour;
	@Column(name = "SCENARIO")
	private Character scenario;
	@Column(name = "TYPE")
	private Character type;
	@Column(name = "IP_ADDRESS")
	private String ipAddress;
	@Column(name = "IP_STATE", length = 40)
	private String ipState;
	@Column(name = "IP_POSTAL_CODE", length = 13)
	private String ipPostalCode;
	@Column(name = "IP_COUNTRY", length = 2)
	private String ipCountry;
	@Column(name = "PROXY")
	private String isProxy;
	@Column(name = "B_LANGUAGE", length = 10)
	private String browserLanguage;
	@Column(name = "P_INTRUMENT_TYPE", length = 16)
	private String paymentInstrumentType;
	@Column(name = "CARD_TYPE", length = 8)
	private String cardType;
	@Column(name = "P_BILLING_ZIP", length = 13)
	private String paymentBillingPostalCode;
	@Column(name = "P_BILLING_STATE", length = 40)
	private String paymentBillingState;
	@Column(name = "P_BILLING_COUNTRY_CODE", length = 2)
	private String paymentBillingCountryCode;
	@Column(name = "SHIPPING_ZIP", length = 13)
	private String shippingPostalCode;
	@Column(name = "SHIPPING_STATE", length = 40)
	private String shippingState;
	@Column(name = "SHIPPING_COUNTRY", length = 2)
	private String shippingCountry;
	@Column(name = "CVV_VERIICATION_RESULT", length = 2)
	private Character cvvVerifyResult;
	@Column(name = "DIGITAL_ITEM_COUNT")
	private Integer digitalItemCount;
	@Column(name = "PHYSICAL_ITEM_COUNT")
	private Integer physicalItemCount;
	@Column(name = "TRANSACTION_DATETIME", length = 25)
	private String transactionDateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Integer getLocalHour() {
		return localHour;
	}
	public void setLocalHour(Integer localHour) {
		this.localHour = localHour;
	}
	public Character getScenario() {
		return scenario;
	}
	public void setScenario(Character scenario) {
		this.scenario = scenario;
	}
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIpState() {
		return ipState;
	}
	public void setIpState(String ipState) {
		this.ipState = ipState;
	}
	public String getIpPostalCode() {
		return ipPostalCode;
	}
	public void setIpPostalCode(String ipPostalCode) {
		this.ipPostalCode = ipPostalCode;
	}
	public String getIpCountry() {
		return ipCountry;
	}
	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}
	public String getIsProxy() {
		return isProxy;
	}
	public void setIsProxy(String isProxy) {
		this.isProxy = isProxy;
	}
	public String getBrowserLanguage() {
		return browserLanguage;
	}
	public void setBrowserLanguage(String browserLanguage) {
		this.browserLanguage = browserLanguage;
	}
	public String getPaymentInstrumentType() {
		return paymentInstrumentType;
	}
	public void setPaymentInstrumentType(String paymentInstrumentType) {
		this.paymentInstrumentType = paymentInstrumentType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPaymentBillingPostalCode() {
		return paymentBillingPostalCode;
	}
	public void setPaymentBillingPostalCode(String paymentBillingPostalCode) {
		this.paymentBillingPostalCode = paymentBillingPostalCode;
	}
	public String getPaymentBillingState() {
		return paymentBillingState;
	}
	public void setPaymentBillingState(String paymentBillingState) {
		this.paymentBillingState = paymentBillingState;
	}
	public String getPaymentBillingCountryCode() {
		return paymentBillingCountryCode;
	}
	public void setPaymentBillingCountryCode(String paymentBillingCountryCode) {
		this.paymentBillingCountryCode = paymentBillingCountryCode;
	}
	public String getShippingPostalCode() {
		return shippingPostalCode;
	}
	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public Character getCvvVerifyResult() {
		return cvvVerifyResult;
	}
	public void setCvvVerifyResult(Character cvvVerifyResult) {
		this.cvvVerifyResult = cvvVerifyResult;
	}
	public Integer getDigitalItemCount() {
		return digitalItemCount;
	}
	public void setDigitalItemCount(Integer digitalItemCount) {
		this.digitalItemCount = digitalItemCount;
	}
	public Integer getPhysicalItemCount() {
		return physicalItemCount;
	}
	public void setPhysicalItemCount(Integer physicalItemCount) {
		this.physicalItemCount = physicalItemCount;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	} 
}