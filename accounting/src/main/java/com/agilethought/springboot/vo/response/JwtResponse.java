package com.agilethought.springboot.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Values class for response to generate a JWT
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class JwtResponse {
	@JsonProperty("token")
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
