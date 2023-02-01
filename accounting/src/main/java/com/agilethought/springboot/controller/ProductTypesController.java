package com.agilethought.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.springboot.app.ProductTypeService;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.ProductTypeVO;
import com.agilethought.springboot.vo.request.AddProductTypeRequestVO;
import com.agilethought.springboot.vo.request.UpdateProductTypeRequestVO;
import com.agilethought.springboot.vo.response.AddProductTypeResponseVO;
import com.agilethought.springboot.vo.response.DeleteProductTypeResponseVO;
import com.agilethought.springboot.vo.response.UpdateProductTypeResponseVO;

@RestController
@RequestMapping("/api/account-types")
public class ProductTypesController {
	@Autowired
	private ProductTypeService accountTypeService;
	@GetMapping()
	public ResponseEntity<List<ProductTypeVO>> getAllAccountTypes(){
		return new ResponseEntity<>(accountTypeService.getAllAccountTypes(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<AddProductTypeResponseVO> addProductType(@RequestBody AddProductTypeRequestVO request){
		return new ResponseEntity<>(accountTypeService.addProductType(request), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductTypeVO> getAllAccountTypes(@PathVariable Long id) throws BusinessException{
		return new ResponseEntity<>(accountTypeService.getAccountType(id), HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<UpdateProductTypeResponseVO> updateProductType(@RequestBody UpdateProductTypeRequestVO request, @PathVariable("id") Long id) throws BusinessException{
		return new ResponseEntity<>(accountTypeService.updateProductType(request, id), HttpStatus.OK);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteProductTypeResponseVO> deleteProductType(@PathVariable Long id){
		return new ResponseEntity<>(accountTypeService.deleteProductType(id), HttpStatus.OK);
	}
}
