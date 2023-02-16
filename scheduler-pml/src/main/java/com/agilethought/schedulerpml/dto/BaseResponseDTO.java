package com.agilethought.schedulerpml.dto;

/**
 * DTO for simple message reponse
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
public class BaseResponseDTO {
	
	
	public BaseResponseDTO(String message) {
		super();
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
