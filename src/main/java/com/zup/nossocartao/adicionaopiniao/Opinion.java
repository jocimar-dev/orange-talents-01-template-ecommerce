package com.zup.nossocartao.adicionaopiniao;

import com.zup.nossocartao.RegisterProduct.Product;
import com.zup.nossocartao.user.Users;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Opinion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Min(1) @Max(5) int note;
	private @NotBlank String title;
	private @NotBlank @Size(max = 500) String descricao;
	@ManyToOne
	private @NotNull @Valid Product product;
	@ManyToOne
	private @NotNull @Valid Users consumer;
	
	@Deprecated
	public Opinion() {

	}

	public Opinion(@Min(1) @Max(5) int note,
				   @NotBlank String title,
				   @NotBlank @Size(max = 500) String descricao,
				   @NotNull @Valid Product product,
				   @NotNull @Valid Users consumer) {
		this.note = note;
		this.title = title;
		this.descricao = descricao;
		this.product = product;
		this.consumer = consumer;
	}

	public String getTitle() {
		return title;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Opinion.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("note=" + note)
				.add("title='" + title + "'")
				.add("descricao='" + descricao + "'")
				.add("product=" + product)
				.add("consumer=" + consumer)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Opinion)) return false;
		Opinion opinion = (Opinion) o;
		return getNote() == opinion.getNote() &&
												Objects.equals(id, opinion.id) &&
												Objects.equals(getTitle(), opinion.getTitle()) &&
												Objects.equals(getDescricao(), opinion.getDescricao()) &&
												Objects.equals(product, opinion.product) &&
												Objects.equals(consumer, opinion.consumer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, getNote(), getTitle(), getDescricao(), product, consumer);
	}

	private Integer getNote() {
		return note;
	}
}
