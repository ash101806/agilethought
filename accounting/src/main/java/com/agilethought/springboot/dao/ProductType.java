package com.agilethought.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Entity class to manage PRODUCT_TYPE table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name =  "PRODUCT_TYPE")
public class ProductType {
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "seq_productTypes", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_productTypes", sequenceName = "SEQ_PRODUCT_TYPES", allocationSize = 1)
	private Long id;
	@Column(name = "CODE")
	private String code;
	@Column(name = "CURRENCY")
	private String currency;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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