package com.agilethought.springboot.app;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.ProductTypeVO;
import com.agilethought.springboot.vo.request.AddProductTypeRequestVO;
import com.agilethought.springboot.vo.request.UpdateProductTypeRequestVO;
import com.agilethought.springboot.vo.response.AddProductTypeResponseVO;
import com.agilethought.springboot.vo.response.DeleteProductTypeResponseVO;
import com.agilethought.springboot.vo.response.UpdateProductTypeResponseVO;
/**
 * Interface to define methods for Product Type CRUD
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Service
public interface ProductTypeService {
	/**
	 * Method to get all product Types
	 * @return a List of {@linkplain ProductTypeVO} whit all Product Types avaiable
	 */
	public List<ProductTypeVO> getAllAccountTypes();
	/**
	 * Method to add a product type
	 * @param request information of new product type {@link AddProductTypeRequestVO}
	 * @return confirmation of created product type see {@link AddProductTypeResponseVO} for more
	 */
	public AddProductTypeResponseVO addProductType(AddProductTypeRequestVO request);
	/**
	 * Method to update a product type
	 * @param new information of product type
	 * @param id Identifier of product type
	 * @return confirmation of updated product type, see {@link UpdateProductTypeResponseVO} for more
	 * @throws BusinessException when can't find an product type with the specified ID
	 */
	public UpdateProductTypeResponseVO updateProductType(UpdateProductTypeRequestVO request, Long id) throws BusinessException;
	/**
	 * Method to delete a product type
	 * @param id Identifier of product type
	 * @return confirmation data of deleted product type
	 */
	public DeleteProductTypeResponseVO deleteProductType(Long id);
	/**
	 * 
	 * @param id
	 * @return The information of product type, see {@link ProductTypeVO} for more
	 * @throws BusinessException when can't find an product type with the specified ID
	 */
	public ProductTypeVO getAccountType(Long id) throws BusinessException;
}
