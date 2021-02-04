package com.zup.nossocartao.registerproduct;

import com.zup.nossocartao.category.Category;
import com.zup.nossocartao.user.Users;
import com.zup.nossocartao.validator.ChecksId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

public class NewProductRequest {

	@NotBlank
	//@UniqueValue(domainClass = Product.class,fieldName = "nome")
	private String name;
	@Positive
	@NotNull
	private Integer quantity;
	@NotBlank
	@Length(max = 1000)
	private String description;
	@NotNull
	@Positive
	private BigDecimal value;
	@NotNull
	@ChecksId(domainClass = Category.class, fieldName = "id")
	private Long idCategory;
	@Size(min = 3)
	@Valid
	// 1
	private List<NewFeaturesRequests> features = new ArrayList<>();

	public NewProductRequest(@NotBlank String name, @Positive int quantity,
							 @NotBlank @Length(max = 1000) String description,
							 @NotNull @Positive BigDecimal value, @NotNull Long idCategory,
							 List<NewFeaturesRequests> caracteristicas) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.value = value;
		this.idCategory = idCategory;
		this.features.addAll(features);
	}

	public List<NewFeaturesRequests> getFeatures() {
		return features;
	}

	public void setFeatures(
			List<NewFeaturesRequests> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", NewProductRequest.class.getSimpleName() + "[", "]")
				.add("name='" + name + "'")
				.add("quantity=" + quantity)
				.add("description='" + description + "'")
				.add("value=" + value)
				.add("idCategory=" + idCategory)
				.add("features=" + features)
				.toString();
	}

	public Product toModel(EntityManager manager, Users owner) {

		Category category = manager.find(Category.class, idCategory);


		return new Product(name, quantity, description, value, category, owner,
				features);
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (NewFeaturesRequests caracteristica : features) {
			String nome = caracteristica.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

}
