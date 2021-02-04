package com.zup.nossocartao.acquisition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PayFeedbackPagseguroRequest implements PayFeedbackGateway {

	@NotBlank
	private String idTransaction;
	@NotNull
	private StatusPagseguro status;
	
	public PayFeedbackPagseguroRequest(@NotBlank String idTransaction,
									   StatusPagseguro status) {
		super();
		this.idTransaction = idTransaction;
		this.status = status;
	}



	public Transaction toTransaction(Purchase purchase) {
		return new Transaction(status.standardize(), idTransaction, purchase);
	} 
	
	
	
	
}
