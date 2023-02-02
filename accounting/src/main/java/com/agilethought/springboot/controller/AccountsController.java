package com.agilethought.springboot.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.springboot.app.AccountService;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountRequestVO;
import com.agilethought.springboot.vo.response.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.FindAccountResponseVO;
/**
 * Class for map the endpoints to account managing
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountsController {
	@Autowired
	private AccountService accountService;
	/**
	 * Method for add an account
	 * @param request information of new account, need to match with the valitions, see more {@link AddAccountRequestVO}
	 * @return Instance of {@link ResponseEntity} and {@link AddAccountResponseVO} standar http response and the information of
	 * created account
	 * @throws NoSuchAlgorithmException when Secure Random can't not be inicialized
	 * @throws BusinessException request not match with business logic
	 */
	@PostMapping
	public ResponseEntity<AddAccountResponseVO> addAccount(@Valid @RequestBody AddAccountRequestVO request) throws NoSuchAlgorithmException, BusinessException{
		return new ResponseEntity<>(accountService.addAccount(request), HttpStatus.OK);
	}
	/**
	 * Method to query the information of an account by account number
	 * @param accountNumber the account number
	 * @return Instance of {@link ResponseEntity} and {@link FindAccountResponseVO} standar http response and the information of
	 *  account
	 * @throws BusinessException
	 */
	@GetMapping("/{account-number}")
	public ResponseEntity<FindAccountResponseVO> getAccount(
			 @NotEmpty @Pattern(regexp = "^[0-9]{11}$", message = "{validations.accounts.number.invalid}") @PathVariable(value = "account-number", required = true) String accountNumber)
			throws BusinessException {
		return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
	}
}
