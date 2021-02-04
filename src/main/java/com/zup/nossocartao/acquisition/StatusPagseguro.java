package com.zup.nossocartao.acquisition;

public enum StatusPagseguro {

	SUCESSO,ERRO;

	public StatusTransacao standardize() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		
		return StatusTransacao.erro;
	}
}
