package com.agilethought.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * Entity class to manage PERSON table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "Person")
public class Person {
	@Id
	@Column(name = "Id",columnDefinition="uniqueidentifier" )
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String personId;
	@Column(name = "Name" )
	private String name;
	@Column(name = "Address" )
	private String address;
	@Column(name = "IdentificationNumber" )
	private String identificationNumber;
	@Column(name = "OnboradingComplete")
	private Boolean onBoardingComplete;
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public Boolean getOnBoardingComplete() {
		return onBoardingComplete;
	}
	public void setOnBoardingComplete(Boolean onBoardingComplete) {
		this.onBoardingComplete = onBoardingComplete;
	}
}
