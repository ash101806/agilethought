package com.agilethought.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.springboot.utils.JwtUtil;
import com.agilethought.springboot.vo.request.JwtRequest;
import com.agilethought.springboot.vo.response.JwtResponse;
/**
 * Class to Map endpoints for login and gettinng JWT
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService  userDetailsService;
	/**
	 * Method to generate a JWT authenticating an user
	 * @param authenticationRequest
	 * @return the jwt value
	 * @throws Exception When credentials or configuration are not ok
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	/**
	 * Method to authenticate an user and password using Spring Security Library
	 * @param username The username
	 * @param password the Password
	 * @throws Exception When the credentials or configuration aren't ok
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
