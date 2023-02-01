package com.agilethought.springboot.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.springboot.app.AccountService;
import com.agilethought.springboot.error.BusinessException;
import com.agilethought.springboot.vo.request.AddAccountResponseVO;
import com.agilethought.springboot.vo.response.AddAccountRequestVO;
import com.agilethought.springboot.vo.response.FindAccountResponseVO;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<AddAccountResponseVO> addAccount(@RequestBody AddAccountRequestVO request) throws NoSuchAlgorithmException, BusinessException{
		return new ResponseEntity<>(accountService.addAccount(request), HttpStatus.OK);
	}
	@GetMapping("/{account-number}")
	public ResponseEntity<FindAccountResponseVO> addAccount(@PathVariable("account-number") String accountNumber) throws  BusinessException{
		return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
	}
}
