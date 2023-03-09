package com.agilethought.springboot.vo.response;

import java.util.List;

import com.agilethought.springboot.vo.ClientInformationDTO;
import com.agilethought.springboot.vo.ProductVO;
/**
 * Values class for show information of an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class FindAccountResponseVO {
	private String id;
	private String accountNumber;
	private String mainProductType;
	private String mainProductId;
	private List<ProductVO> products;
	private List<ProductVO> internationalProducts;
	private ClientInformationDTO client;
	
	public List<ProductVO> getInternationalProducts() {
		return internationalProducts;
	}
	public void setInternationalProducts(List<ProductVO> internationalProducts) {
		this.internationalProducts = internationalProducts;
	}
	public ClientInformationDTO getClient() {
		return client;
	}
	public void setClient(ClientInformationDTO client) {
		this.client = client;
	}
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
	public String getMainProductType() {
		return mainProductType;
	}
	public void setMainProductType(String mainProductType) {
		this.mainProductType = mainProductType;
	}
	public String getMainProductId() {
		return mainProductId;
	}
	public void setMainProductId(String mainProductId) {
		this.mainProductId = mainProductId;
	}
	public List<ProductVO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}
}