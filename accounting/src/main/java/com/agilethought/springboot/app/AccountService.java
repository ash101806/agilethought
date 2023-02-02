package com.agilethought.springboot.app;

import java.security.NoSuchAlgorithmException;

import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountRequestVO;
import com.agilethought.springboot.vo.response.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.FindAccountResponseVO;
/**
 * Interface to define methods for Account Managing
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public interface AccountService {
	/**
	 * Service layer method to create a new account
	 * @param request of type {@link AddAccountRequestVO} with new account information
	 * @return response of type {@link AddAccountResponseVO}
	 * @throws BusinessException When Business model can't process account (p.e. invalid client)
	 * @throws NoSuchAlgorithmException When securedRandom can't inicialize
	 */
	public AddAccountResponseVO addAccount(AddAccountRequestVO request) throws BusinessException, NoSuchAlgorithmException;
	/**
	 * Service layer method to create a new account
	 * @param accountNumber Account number in right format
	 * @return acount information, see type {@link FindAccountResponseVO} for more information
	 * @throws BusinessException When account does not exists
	 */
	public FindAccountResponseVO findAccount(String accountNumber) throws BusinessException;
}
