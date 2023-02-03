package com.agilethought.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Entity class to manage PERSON table
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Entity
@Table(name = "PERSON")
public class Person {
	@Id
	@Column(name = "ID" )
	@GeneratedValue(generator = "seq_person", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_person", sequenceName = "SEQ_PERSON", allocationSize = 1)
	private Long personId;
	@Column(name = "NAME" )
	private String name;
	@Column(name = "ADDRESS" )
	private String address;
	@Column(name = "IDENTIFICATION_NUMBER" )
	private String identificationNumber;
	@Column(name = "ONBORADING_COMPLETE")
	private Boolean onBoardingComplete;
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
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
