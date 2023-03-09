package com.agilethought.springboot.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * Entity class to manage ACCOUNT table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "Account")
public class Account {
	@Id
	@Column(name = "Id",columnDefinition="uniqueidentifier")
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	@Column(name = "AccountNumber", length = 11)
	private String accountNumber;
	@Column(name = "CotractNumber", length = 18)
	private String contract;
	@Column(name = "PersonId",columnDefinition="uniqueidentifier")
	private String personId;
	@Column(name = "MainProductId",columnDefinition="uniqueidentifier")
	private String mainProductId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PersonId", insertable = false, updatable = false)
	private Person person;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MainProductId" , insertable = false, updatable = false)
	private Product mainProduct;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<Product> products;

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(String mainProductId) {
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