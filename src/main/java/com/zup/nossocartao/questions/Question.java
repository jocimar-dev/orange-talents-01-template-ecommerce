package com.zup.nossocartao.questions;

import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.user.Users;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Question implements Comparable<Question>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String title;

	@ManyToOne
	@Valid
	@NotNull
	private Users interested;

	@ManyToOne
	@Valid
	@NotNull
	private Product product;

	private LocalDate instant;
	
	@Deprecated
	public Question() {

	}

	public Question(@NotBlank String title,
					@NotNull @Valid Users interested,
					@NotNull @Valid Product product) {
		this.title = title;
		this.interested = interested;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Users getInterested() {
		return interested;
	}

	public Product getProduct() {
		return product;
	}

	public LocalDate getInstant() {
		return instant;
	}


	public Users getProductOwner() {
		return product.getOwner();
	}

	@Override
	public int compareTo(Question o) {
		return this.title.compareTo(o.title);
	}

	public String getTitulo() {
		return title;
	};
}
