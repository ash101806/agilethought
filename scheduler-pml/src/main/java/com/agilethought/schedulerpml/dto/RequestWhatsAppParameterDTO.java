package com.agilethought.schedulerpml.dto;
/**
 * DTO for whatsapp send message request
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class RequestWhatsAppParameterDTO {

	private String type;
	private String text;

	public RequestWhatsAppParameterDTO(String value) {
		this.text = value;
		this.type = "text";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
