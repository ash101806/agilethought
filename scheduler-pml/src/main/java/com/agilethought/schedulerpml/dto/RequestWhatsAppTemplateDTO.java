package com.agilethought.schedulerpml.dto;

import java.util.List;
/**
 * DTO for whatsapp send message request
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class RequestWhatsAppTemplateDTO {
	private String name;
	private RequestWhatsAppLanguageDTO language;
	private List<RequestWhatsAppComponentsDTO> components;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RequestWhatsAppLanguageDTO getLanguage() {
		return language;
	}
	public void setLanguage(RequestWhatsAppLanguageDTO language) {
		this.language = language;
	}
	public List<RequestWhatsAppComponentsDTO> getComponents() {
		return components;
	}
	public void setComponents(List<RequestWhatsAppComponentsDTO> components) {
		this.components = components;
	}
}
