package com.zup.nossocartao.questions;

import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.user.UserRepository;
import com.zup.nossocartao.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewQuestionsController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Emails emails;	

	@PostMapping(value = "/api/produtos/{id}/perguntas")
	@Transactional
	public String cria(@RequestBody @Valid NewQuestionsRequest request, @PathVariable("id") Long id) {

		Product product = manager.find(Product.class,id);

		Users interested = userRepository.findByEmail("teste_zupper@zup.com.br").get();

		Question newQuestions = request.toModel(interested,product);
		manager.persist(newQuestions);
		
		emails.novaPergunta(newQuestions);
		
		return newQuestions.toString();
	}

}
