package com.agilethought.schedulerpml.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * DTO for whatsapp send message request
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class RequestWhatsAppDTO {
	@JsonProperty("messaging_product")
	private String product;
	private String to;
	private String type;
	private RequestWhatsAppTemplateDTO template;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public RequestWhatsAppTemplateDTO getTemplate() {
		return template;
	}
	public void setTemplate(RequestWhatsAppTemplateDTO template) {
		this.template = template;
	}
}
