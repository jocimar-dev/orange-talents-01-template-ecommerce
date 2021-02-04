package com.zup.nossocartao.acquisition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PayFeedbackPaypalRequest implements PayFeedbackGateway {

	@Min(0)
	@Max(1)
	private int status;
	@NotBlank
	private String idTransacao;

	public PayFeedbackPaypalRequest(@Min(0) @Max(1) int status,
									@NotBlank String idTransacao) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
	}

	public Transaction toTransaction(Purchase purchase) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro
				: StatusTransacao.sucesso;
		
		return new Transaction(statusCalculado, idTransacao, purchase);
	}

}
