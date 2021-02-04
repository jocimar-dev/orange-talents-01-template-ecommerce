package com.zup.nossocartao.acquisition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PurchaseFeedbackController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	//1	
	private NewPurchase newPurchase;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	//1
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid PayFeedbackPagseguroRequest request) {
		return processa(idCompra, request);
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	//1
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid PayFeedbackPaypalRequest request) {
		return processa(idCompra, request);
	}
	
	//1
	private String processa(Long idCompra, PayFeedbackGateway payFeedbackGateway) {
		//1
		Purchase purchase = manager.find(Purchase.class, idCompra);
		purchase.addTransaction(payFeedbackGateway);
		manager.merge(purchase);
		newPurchase.processa(purchase);
		
		return purchase.toString();
	}

}
