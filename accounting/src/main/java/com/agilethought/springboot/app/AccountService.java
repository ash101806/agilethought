package com.agilethought.springboot.app;

import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.AddAccountRequestVO;

public interface AccountService {
	public AddAccountResponseVO addAccount(AddAccountRequestVO request) throws BusinessException;
}
