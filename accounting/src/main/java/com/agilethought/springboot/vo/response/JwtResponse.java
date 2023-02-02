package com.agilethought.springboot.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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
