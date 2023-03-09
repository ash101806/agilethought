package com.agilethought.springboot.app;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.agilethought.springboot.dao.ProductType;
import com.agilethought.springboot.dao.ProductTypeRepository;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.ProductTypeVO;
import com.agilethought.springboot.vo.request.AddProductTypeRequestVO;
import com.agilethought.springboot.vo.request.UpdateProductTypeRequestVO;
import com.agilethought.springboot.vo.response.AddProductTypeResponseVO;
import com.agilethought.springboot.vo.response.DeleteProductTypeResponseVO;
import com.agilethought.springboot.vo.response.UpdateProductTypeResponseVO;
/**
 * Implementation class for product type CRUD
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Autowired
	private ProductTypeRepository productTypeRepo;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductTypeVO> getAllAccountTypes() {
		return StreamSupport.stream(productTypeRepo.findAll().spliterator(), false).map(type -> {
			ProductTypeVO rt = new ProductTypeVO();
			BeanUtils.copyProperties(type, rt);
			return rt;
		}).collect(Collectors.toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AddProductTypeResponseVO addProductType(AddProductTypeRequestVO request) {
		ProductType productType = new ProductType();
		BeanUtils.copyProperties(request, productType);
		productTypeRepo.save(productType);
		AddProductTypeResponseVO response = new AddProductTypeResponseVO();
		BeanUtils.copyProperties(productType, response);
		return response;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpdateProductTypeResponseVO updateProductType(UpdateProductTypeRequestVO request,String id) throws BusinessException {
		ProductType productType = productTypeRepo.findById(id).orElseThrow(
				() -> new BusinessException(HttpStatus.BAD_REQUEST, "Can't find product with the provided id"));
		BeanUtils.copyProperties(request, productType);
		productType.setId(id);
		productTypeRepo.save(productType);
		UpdateProductTypeResponseVO response = new UpdateProductTypeResponseVO();
		BeanUtils.copyProperties(productType, response);
		return response;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeleteProductTypeResponseVO deleteProductType(String id) {
		productTypeRepo.deleteById(id);
		DeleteProductTypeResponseVO response = new DeleteProductTypeResponseVO();
		response.setId(id);
		return response;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductTypeVO getAccountType(String id) throws BusinessException{
		ProductType productType = productTypeRepo.findById(id).orElseThrow(
				() -> new BusinessException(HttpStatus.BAD_REQUEST, "Can't find Product with the provided id"));
		ProductTypeVO response = new ProductTypeVO();
		BeanUtils.copyProperties(productType, response);
		return response;
	}

}
