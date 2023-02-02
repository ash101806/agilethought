package com.agilethought.springboot.vo.response;
/**
 * Values class for response to create an Product to an account
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class AddProductResponseVO {
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}