package com.zup.nossocartao.registerproduct;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class NewFeaturesRequests {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public NewFeaturesRequests(@NotBlank String nome,
							   @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", NewFeaturesRequests.class.getSimpleName() + "[", "]")
				.add("nome='" + nome + "'")
				.add("descricao='" + descricao + "'")
				.toString();
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public ProductFeature toModel(@NotNull @Valid Product product) {
		return new ProductFeature(nome,descricao, product);
	}
	
	

}
