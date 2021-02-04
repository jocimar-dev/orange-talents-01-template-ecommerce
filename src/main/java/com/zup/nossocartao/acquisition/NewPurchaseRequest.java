package com.zup.nossocartao.acquisition;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewPurchaseRequest {

	@Positive
	private int quantity;
	@NotNull
	private Long idProduct;
	@NotNull
	private PayGateway gateway;
	
	public NewPurchaseRequest(@Positive int quantity,
							  @NotNull Long idProduct, PayGateway gateway) {
		super();
		this.quantity = quantity;
		this.idProduct = idProduct;
		this.gateway = gateway;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public PayGateway getGateway() {
		return gateway;
	}
	
	
}
