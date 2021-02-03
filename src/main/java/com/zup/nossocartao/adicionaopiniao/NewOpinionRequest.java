package com.zup.nossocartao.adicionaopiniao;

import com.zup.nossocartao.RegisterProduct.Product;
import com.zup.nossocartao.user.Users;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class NewOpinionRequest {

	@Min(1)
	@Max(5)
	private int note;
	@NotBlank
	private String title;
	@NotBlank
	@Size(max = 500)
	private String description;

	public NewOpinionRequest(@Min(1) @Max(5) int note, @NotBlank String title,
							 @NotBlank @Size(max = 500) String description) {
		super();
		this.note = note;
		this.title = title;
		this.description = description;
	}

	public Opinion toModel(@NotNull @Valid Product product, @NotNull @Valid Users consumer) {
		return new Opinion(note, title, description,product,consumer);
	}


}
