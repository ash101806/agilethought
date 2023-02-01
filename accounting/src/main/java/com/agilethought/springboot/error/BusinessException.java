package com.agilethought.springboot.error;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -3062494115949936897L;
	private HttpStatus status;
	private String message;
	public BusinessException(HttpStatus status, String message) {
		this.message = message;
		this.status = status;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
