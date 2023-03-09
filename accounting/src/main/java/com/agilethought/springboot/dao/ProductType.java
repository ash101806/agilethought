package com.agilethought.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * Entity class to manage PRODUCT_TYPE table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name =  "ProductType")
public class ProductType {
	@Id
	@Column(name = "Id",columnDefinition="uniqueidentifier")
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	@Column(name = "Code", length = 5)
	private String code;
	@Column(name = "CurrencyCode", length = 6)
	private String currency;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}