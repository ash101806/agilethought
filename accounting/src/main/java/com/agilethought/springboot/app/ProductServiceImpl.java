package com.agilethought.springboot.app;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.agilethought.springboot.dao.Account;
import com.agilethought.springboot.dao.AccountRepository;
import com.agilethought.springboot.dao.Product;
import com.agilethought.springboot.dao.ProductRepository;
import com.agilethought.springboot.dao.ProductType;
import com.agilethought.springboot.dao.ProductTypeRepository;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddProductRequestVO;
import com.agilethought.springboot.vo.response.AddProductResponseVO;

/**
 * Service class for manage product 
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Service
public class ProductServiceImpl implements ProductService {
	private static final Character ACTIVE_STATUS = 'A';
	private static final Character PENDING_STATUS = 'P';
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private ProductTypeRepository productTypeRepo;
	@Autowired
	private ProductRepository productRepository;
	@Value("${agilethought.config.curency.national}")
	private String nationalCurrency;
	@Value("${agilethought.config.account.prefix}")
	private String prefix;
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public AddProductResponseVO addProductToAnAccount(AddProductRequestVO request) throws BusinessException {
		Account account = accountRepo.findByAccountNumber(request.getAccountNumber())
				.orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST, "Can't fin the specified account"));
		
		if(account.getPersonId().equals(request.getClientId()))
			throw new BusinessException(HttpStatus.FORBIDDEN, "The provided client id doesn't match with the account");
		
		ProductType productType = productTypeRepo.findByCode(request.getProductTypeCode()).orElseThrow(
				() -> new BusinessException(HttpStatus.BAD_REQUEST, "Can't find product with the provided code"));
		
		Product product = new Product();
		product.setActivationDate(LocalDateTime.now());
		product.setBalance(request.getInitialBalance());
		product.setProductTypeId(productType.getId());
		product.setStatus(productType.getCurrency().equals(nationalCurrency)? ACTIVE_STATUS : PENDING_STATUS);
		product.setAccountId(account.getId());
		productRepository.save(product);
		
		AddProductResponseVO response = new AddProductResponseVO();
		response.setProductId(product.getId());
		
		return response;
	}
}