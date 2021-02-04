package com.zup.nossocartao.questions;

import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.user.Users;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewQuestionsRequest {

	@NotBlank
	private String title;
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "NewQuestionsRequest [title=" + title + "]";
	}

	public Question toModel(@NotNull @Valid Users interested,
							@NotNull @Valid Product product) {
		return new Question(title, interested, product);
	}

}
