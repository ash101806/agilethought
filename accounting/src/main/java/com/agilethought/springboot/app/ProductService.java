package com.agilethought.springboot.app;

import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddProductRequestVO;
import com.agilethought.springboot.vo.response.AddProductResponseVO;
/**
 * Class for Product managing
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
public interface ProductService {
	/**
	 * Method to add a Product to existing account
	 * @param request the information o account and new product, see more {@link AddProductRequestVO}
	 * @return the information of new product, see more {@link AddProductResponseVO}
	 * @throws BusinessException
	 */
	public AddProductResponseVO addProductToAnAccount(AddProductRequestVO request)throws BusinessException;
}
