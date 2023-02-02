package com.agilethought.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.agilethought.springboot.dao.AccountRepository;
import com.agilethought.springboot.dao.Person;
import com.agilethought.springboot.dao.ProductType;
import com.agilethought.springboot.dao.ProductTypeRepository;
import com.agilethought.springboot.vo.request.AddAccountRequestVO;
import com.agilethought.springboot.vo.request.AddProductTypeRequestVO;
import com.agilethought.springboot.vo.request.JwtRequest;
import com.agilethought.springboot.vo.request.UpdateProductTypeRequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
class AccountingApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ProductTypeRepository productTyperepo;
	@Autowired
	AccountRepository acountRepository;
	@Autowired
	EntityManager em;
	@Test
	@Order(1)
	public void allProductsTypes() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/api/product-types").header("Authorization", getToken())).andExpect(status().isOk());
	}
	@Test
	@Order(3)
	public void getProductTypeById() throws JsonProcessingException, Exception {
		ProductType pt = StreamSupport.stream(productTyperepo.findAll().spliterator(), false).findAny()
				.orElseThrow(() -> new Exception("Can't test getProductTypeById method of productType"));
		mockMvc.perform(get("/api/product-types/{id}", pt.getId()).header("Authorization", getToken()))
				.andExpect(status().isOk());
	}
	@Test
	@Order(2)
	public void addProductType() throws JsonProcessingException, Exception {
		ObjectMapper  mapper = new  ObjectMapper();
		AddProductTypeRequestVO request = new AddProductTypeRequestVO();
		request.setCode("EFE");
		request.setCurrency("MXP");
		
		mockMvc.perform(post("/api/product-types").header("Authorization", getToken())
				.content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	@Order(4)
	public void updateProductType() throws JsonProcessingException, Exception {
		ObjectMapper  mapper = new  ObjectMapper();
		UpdateProductTypeRequestVO request = new UpdateProductTypeRequestVO();
		
		ProductType pt = StreamSupport.stream(productTyperepo.findAll().spliterator(), false).findAny()
				.orElseThrow(() -> new Exception("Can't test update method of productType"));
		request.setCode(pt.getCode());
		request.setCurrency("MXP");
		mockMvc.perform(put("/api/product-types/{id}",pt.getId()).header("Authorization", getToken())
				.content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	@Order(5)
	public void deleteProductType() throws JsonProcessingException, Exception {
		
		ProductType pt = StreamSupport.stream(productTyperepo.findAll().spliterator(), false).findAny()
				.orElseThrow(() -> new Exception("Can't test update method of productType"));
		mockMvc.perform(delete("/api/product-types/{id}",pt.getId()).header("Authorization", getToken())).andExpect(status().isOk());
	}
	@Test
	@Order(6)
	public void addAccount() throws JsonProcessingException, Exception  {
		ObjectMapper  mapper = new  ObjectMapper();
		ProductType pt = StreamSupport.stream(productTyperepo.findAll().spliterator(), false).findAny()
				.orElseThrow(() -> new Exception("Can't test update method of productType"));
		
		Person client = em.createQuery("from Person where onBoardingComplete = true", Person.class).getSingleResult();
		AddAccountRequestVO request = new AddAccountRequestVO();
		request.setAccountTypeCode(pt.getCode());
		request.setClientId(client.getPersonId());
		request.setInitialBalance(BigDecimal.valueOf(115.50));
		mockMvc.perform(post("/api/accounts").header("Authorization", getToken())
				.content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	@Order(7)
	public void findAccountByNumber() throws JsonProcessingException, Exception  {
		String accountNumber = StreamSupport.stream(acountRepository.findAll().spliterator(), false).findAny()
				.orElseThrow(() -> new Exception("No accounts find to test")).getAccountNumber();
		mockMvc.perform(get("/api/accounts/{accountNumber}", accountNumber).header("Authorization", getToken()))
				.andExpect(status().isOk());
	}
	@Test
	void contextLoads() {
	}
	
	public String getToken() throws JsonProcessingException, Exception {
		JwtRequest request = new JwtRequest();
		request.setPassword("password");
		request.setUsername("agilethought");
		ObjectMapper  mapper = new  ObjectMapper();
		MvcResult mvcResult = mockMvc.perform(post("/authenticate").content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		return "Bearer " + JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");
	}
}
