package com.agilethought.springboot.vo;
/**
 * Class Values for client information
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class ClientInformationDTO {
	private String name;
	private String adress;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}