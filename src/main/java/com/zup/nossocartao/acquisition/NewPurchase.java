package com.zup.nossocartao.acquisition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NewPurchase {

	@Autowired
	//1
	private Set<PurchaseSuccess> eventosCompraSucesso;

	public void processa(Purchase purchase) {
		//1
		if(purchase.processed()) {
			//1
			eventosCompraSucesso.forEach(evento -> evento.processesPurchase(purchase));
		} 
		else {
			//eventosFalha
		}		
	}

}
