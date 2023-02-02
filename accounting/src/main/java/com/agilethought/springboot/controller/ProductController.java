package com.agilethought.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.springboot.app.ProductService;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddProductRequestVO;
import com.agilethought.springboot.vo.response.AddProductResponseVO;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	/**
	 * Method to add a product 
	 * @param request information of new product {@link AddProductRequestVO}
	 * @return confirmation of created product see {@link AddProductResponseVO} for more
	 * @throws BusinessException When a business rule has been broken
	 */
	@PostMapping
	public ResponseEntity<AddProductResponseVO> addProductType(@Valid @RequestBody AddProductRequestVO request) throws BusinessException{
		return new ResponseEntity<>(productService.addProductToAnAccount(request), HttpStatus.OK);
	}
}
