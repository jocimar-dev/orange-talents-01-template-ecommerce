package com.zup.nossocartao.acquisition;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Invoice implements PurchaseSuccess {

	@Override
	public void processesPurchase(Purchase purchase) {
		Assert.isTrue(purchase.processed(),"Opa opa opa compra nao concluida com sucesso "+ purchase);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", purchase.getId(),
				"idComprador", purchase.getBuyer().getId());

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);
	}

}
