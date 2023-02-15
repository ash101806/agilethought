package com.agilethought.schedulerpml.dto;

import java.util.List;
/**
 * DTO for whatsapp send message request
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class RequestWhatsAppComponentsDTO {
	private String type;
	private List<RequestWhatsAppParameterDTO> parameters;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<RequestWhatsAppParameterDTO> getParameters() {
		return parameters;
	}
	public void setParameters(List<RequestWhatsAppParameterDTO> parameters) {
		this.parameters = parameters;
	}
	
}
