package com.zup.nossocartao.others;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class NFRequest {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;

	public NFRequest(Long idCompra, Long idComprador) {
		super();
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", NFRequest.class.getSimpleName() + "[", "]")
				.add("idCompra=" + idCompra)
				.add("idComprador=" + idComprador)
				.toString();
	}

	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdComprador() {
		return idComprador;
	}

}
