package com.zup.nossocartao.adicionaopiniao;

import com.zup.nossocartao.RegisterProduct.Product;
import com.zup.nossocartao.user.Users;
import com.zup.nossocartao.validator.LoggedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpinionController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/api/produtos/{id}/opiniao")
	@Transactional
	// 1
	public String adiciona(@RequestBody @Valid NewOpinionRequest request,
			@PathVariable("id") Long id,@AuthenticationPrincipal LoggedUser loggedUser ) {
		Product product = manager.find(Product.class, id);
		Users consumer = loggedUser.get();
		Opinion newOpinion = request.toModel(product, consumer);
		manager.persist(newOpinion);

		return newOpinion.toString();
	}

}
