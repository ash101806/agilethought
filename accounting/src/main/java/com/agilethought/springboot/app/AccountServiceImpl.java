package com.agilethought.springboot.app;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.agilethought.springboot.dao.Account;
import com.agilethought.springboot.dao.AccountRepository;
import com.agilethought.springboot.dao.Person;
import com.agilethought.springboot.dao.PersonRepository;
import com.agilethought.springboot.dao.Product;
import com.agilethought.springboot.dao.ProductRepository;
import com.agilethought.springboot.dao.ProductType;
import com.agilethought.springboot.dao.ProductTypeRepository;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.AddAccountRequestVO;
import com.agilethought.springboot.vo.response.FindAccountResponseVO;
import com.agilethought.springboot.vo.response.ProductVO;
@Service
public class AccountServiceImpl implements AccountService{
	private static final Character ACTIVE_STATUS = 'A';
	private static final Character PENDING_STATUS = 'P';
	@Autowired
	private ProductTypeRepository productTypeRepo;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AccountRepository accountrepository;
	@Value("${agilethought.config.curency.national}")
	private String nationalCurrency;
	@Value("${agilethought.config.account.prefix}")
	private String prefix;
	@Override
	@Transactional
	public AddAccountResponseVO addAccount(AddAccountRequestVO request) throws BusinessException, NoSuchAlgorithmException {
		ProductType productType = productTypeRepo.findByCode(request.getAccountTypeCode()).orElseThrow(
				() -> new BusinessException(HttpStatus.BAD_REQUEST, "Can't find product with the provided code"));
		Person client = personRepository.findById(request.getClientId())
				.orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST, ""));
		if(!client.getOnBoardingComplete().booleanValue())
			throw new BusinessException(HttpStatus.FORBIDDEN, "Could not open a new account until onborading process was completed");
		
		Product product = new Product();
		product.setActivationDate(LocalDateTime.now());
		product.setBalance(request.getInitialBalance());
		product.setProductTypeId(productType.getId());
		product.setStatus(productType.getCurrency().equals(nationalCurrency)? ACTIVE_STATUS : PENDING_STATUS);
		productRepository.save(product);
		Account account = new Account();
		int randomNumer = SecureRandom.getInstanceStrong().nextInt(999999);
		account.setAccountNumber(prefix + String.format("%06d", randomNumer));
		account.setContract(UUID.randomUUID().toString().toUpperCase());
		account.setMainProductId(product.getId());
		account.setPersonId(client.getPersonId());
		accountrepository.save(account);
		product.setAccountId(account.getId());
		productRepository.save(product);
		AddAccountResponseVO response = new AddAccountResponseVO();
		response.setAccountNumber(account.getAccountNumber());
		response.setProductId(product.getId());
		response.setStatus(product.getStatus());
		response.setContractUUID(account.getContract());
		return response;
	}
	@Override
	public FindAccountResponseVO findAccount(String accountNumber) throws BusinessException {
		Account account = accountrepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Could not find an account with the specified account number"));
		FindAccountResponseVO response = new FindAccountResponseVO();
		response.setAccountNumber(account.getAccountNumber());
		response.setId(account.getId());
		response.setMainProductId(account.getMainProductId());
		response.setMainProductType(account.getMainProduct().getProductType().getCode());
		List<ProductVO> products = account.getProducts().stream().map(product -> {
			ProductVO prt = new ProductVO();
			BeanUtils.copyProperties(product, prt);
			prt.setType(product.getProductType().getCode());
			return prt;
		}).collect(Collectors.toList());
		response.setProducts(products);
		return response;
	}
}
