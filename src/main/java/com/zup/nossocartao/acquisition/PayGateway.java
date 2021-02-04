package com.zup.nossocartao.acquisition;

import org.springframework.web.util.UriComponentsBuilder;

public enum PayGateway {

	pagseguro {
		@Override
		String urlFeedback(Purchase purchase,
						   UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/retorno-pagseguro/{id}")
					.buildAndExpand(purchase.getId()).toString();

			return "pagseguro.com/" + purchase.getId() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		String urlFeedback(Purchase purchase,
						   UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/retorno-paypal/{id}").buildAndExpand(purchase.getId())
					.toString();

			return "paypal.com/" + purchase.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	abstract String urlFeedback(Purchase purchase,
								UriComponentsBuilder uriComponentsBuilder);
}
