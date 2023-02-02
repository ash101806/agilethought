package com.agilethought.springboot.vo.request;

import javax.validation.constraints.NotEmpty;
/**
 * Values class for  generate a JWT
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class JwtRequest {
	@NotEmpty(message = "{validations.login.username}")
	private String username;
	@NotEmpty(message = "{validations.login.password}")
	private String password;
	
	public JwtRequest()
	{
		
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
