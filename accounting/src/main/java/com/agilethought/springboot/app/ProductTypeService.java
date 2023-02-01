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

@Service
public interface ProductTypeService {
	public List<ProductTypeVO> getAllAccountTypes();

	public AddProductTypeResponseVO addProductType(AddProductTypeRequestVO request);

	public UpdateProductTypeResponseVO updateProductType(UpdateProductTypeRequestVO request, Long id) throws BusinessException;

	public DeleteProductTypeResponseVO deleteProductType(Long id);

	public ProductTypeVO getAccountType(Long id) throws BusinessException;
}
