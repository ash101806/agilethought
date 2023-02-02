package com.agilethought.springboot.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Entity class to manage ACCOUNT table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "seq_account", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_account", sequenceName = "SEQ_ACCOUNT", allocationSize = 1)
	private Long id;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "CONTRACT_NUMBER")
	private String contract;
	@Column(name = "PERSON_ID")
	private Long personId;
	@Column(name = "MAIN_PRODUCT_ID")
	private Long mainProductId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", insertable = false, updatable = false)
	private Person person;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAIN_PRODUCT_ID" , insertable = false, updatable = false)
	private Product mainProduct;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<Product> products;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(Long mainProductId) {
		this.mainProductId = mainProductId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Product getMainProduct() {
		return mainProduct;
	}
	public void setMainProduct(Product mainProduct) {
		this.mainProduct = mainProduct;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}