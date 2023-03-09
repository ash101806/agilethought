package com.agilethought.springboot.controller;

import java.util.List;

import javax.validation.Valid;

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
/**
 * Class to map the product Types endpoint
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@RestController
@RequestMapping("/api/product-types")
public class ProductTypesController {
	@Autowired
	private ProductTypeService accountTypeService;
	/**
	 * Method to get all product Types
	 * @return a List of {@linkplain ProductTypeVO} whit all Product Types avaiable
	 */
	@GetMapping()
	public ResponseEntity<List<ProductTypeVO>> getAllAccountTypes(){
		return new ResponseEntity<>(accountTypeService.getAllAccountTypes(), HttpStatus.OK);
	}
	/**
	 * Method to add a product type
	 * @param request information of new product type {@link AddProductTypeRequestVO}
	 * @return confirmation of created product type see {@link AddProductTypeResponseVO} for more
	 */
	@PostMapping
	public ResponseEntity<AddProductTypeResponseVO> addProductType(@Valid @RequestBody AddProductTypeRequestVO request){
		return new ResponseEntity<>(accountTypeService.addProductType(request), HttpStatus.OK);
	}
	/**
	 * Method to get an product type information by id
	 * @param id
	 * @return The information of product type, see {@link ProductTypeVO} for more
	 * @throws BusinessException when can't find an product type with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductTypeVO> getAllAccountTypes(@PathVariable String id) throws BusinessException{
		return new ResponseEntity<>(accountTypeService.getAccountType(id), HttpStatus.OK);
	}
	/**
	 * Method to update a product type
	 * @param new information of product type
	 * @param id Identifier of product type
	 * @return confirmation of updated product type, see {@link UpdateProductTypeResponseVO} for more
	 * @throws BusinessException when can't find an product type with the specified ID
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UpdateProductTypeResponseVO> updateProductType(
			@Valid @RequestBody UpdateProductTypeRequestVO request,
			@PathVariable(value = "id", required = true) String id) throws BusinessException {
		return new ResponseEntity<>(accountTypeService.updateProductType(request, id), HttpStatus.OK);

	}
	/**
	 * Method to delete a product type
	 * @param id Identifier of product type
	 * @return confirmation data of deleted product type
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteProductTypeResponseVO> deleteProductType(@PathVariable String id){
		return new ResponseEntity<>(accountTypeService.deleteProductType(id), HttpStatus.OK);
	}
}
