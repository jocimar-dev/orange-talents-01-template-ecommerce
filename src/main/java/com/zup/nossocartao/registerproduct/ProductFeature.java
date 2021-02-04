package com.zup.nossocartao.registerproduct;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class ProductFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String name;
	private @NotBlank String description;
	@ManyToOne
	private @NotNull @Valid Product product;
	
	@Deprecated
	public ProductFeature() {

	}

	public ProductFeature(@NotBlank String name,
						  @NotBlank String description,
						  @NotNull @Valid Product product) {
		this.name = name;
		this.description = description;
		this.product = product;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductFeature)) return false;
		ProductFeature that = (ProductFeature) o;
		return Objects.equals(id, that.id) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(product, that.product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, getName(), getDescription(), product);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ProductFeature.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name='" + name + "'")
				.add("description='" + description + "'")
				.add("product=" + product)
				.toString();
	}
}
