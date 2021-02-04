package com.zup.nossocartao.others;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class PurchaseRankingRequest {

	@NotNull
	private Long idPurchase;
	@NotNull
	private Long idProductOwner;

	public PurchaseRankingRequest(Long idPurchase, Long idProductOwner) {
		super();
		this.idPurchase = idPurchase;
		this.idProductOwner = idProductOwner;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", PurchaseRankingRequest.class.getSimpleName() + "[", "]")
				.add("idPurchase=" + idPurchase)
				.add("idProductOwner=" + idProductOwner)
				.toString();
	}
}
