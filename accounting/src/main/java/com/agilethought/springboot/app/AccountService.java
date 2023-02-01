package com.agilethought.springboot.app;

import java.security.NoSuchAlgorithmException;

import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.AddAccountRequestVO;
import com.agilethought.springboot.vo.response.FindAccountResponseVO;

public interface AccountService {
	public AddAccountResponseVO addAccount(AddAccountRequestVO request) throws BusinessException, NoSuchAlgorithmException;
	public FindAccountResponseVO findAccount(String accountNumber) throws BusinessException;
}
