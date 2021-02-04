package com.zup.nossocartao.acquisition;

public interface PayFeedbackGateway {

	/**
	 * 
	 * @param purchase
	 * @return uma nova transacao em função do gateway específico
	 */
	Transaction toTransaction(Purchase purchase);

}
