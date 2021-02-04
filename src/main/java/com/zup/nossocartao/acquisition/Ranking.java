package com.zup.nossocartao.acquisition;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements PurchaseSuccess {

	@Override
	public void processesPurchase(Purchase purchase) {
		Assert.isTrue(purchase.processed(),"Esta compra não foi processada "+ purchase);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idPurchase", purchase.getId(),
				"idProductOwner", purchase.getProductOwner().getId());

		restTemplate.postForEntity("http://localhost:8080/ranking",
				request, String.class);		
	}

}
