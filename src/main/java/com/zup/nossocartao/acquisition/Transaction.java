package com.zup.nossocartao.acquisition;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private StatusTransacao status;
	private @NotBlank String idTransacaoGateway;
	@NotNull
	private LocalDateTime instante;
	@ManyToOne
	private @NotNull @Valid Purchase purchase;
	
	@Deprecated
	public Transaction() {

	}

	public Transaction(@NotNull StatusTransacao status,
					   @NotBlank String idTransacaoGateway, @NotNull @Valid Purchase purchase) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.purchase = purchase;
		this.instante = LocalDateTime.now();
	}
	
	public boolean concluded() {
		return this.status.equals(StatusTransacao.sucesso);
	}
	
	

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status
				+ ", idTransacaoGateway=" + idTransacaoGateway + ", instante="
				+ instante + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}
	
	
	
	

}
