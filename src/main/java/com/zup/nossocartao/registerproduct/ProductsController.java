package com.zup.nossocartao.registerproduct;

import com.zup.nossocartao.user.UserRepository;
import com.zup.nossocartao.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ProductsController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Uploader uploaderFake;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new NotAllowFeatureWithDuplicateName());
	}

	@PostMapping(value = "/api/produtos")
	@Transactional
	public String cria(@RequestBody @Valid NewProductRequest request) {
		Users owner = userRepository.findByEmail("jocimar@zup.com").get();
		Product product = request.toModel(manager, owner);
		
		manager.persist(product);
		return product.toString();
	}
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	//1
	public String adicionaImagens(@PathVariable("id") Long id,@Valid NovasImagensRequest request) {
		Users owner = userRepository.findByEmail("testando@zup.com.br").get();
		Product product = manager.find(Product.class, id);
		
		if(!product.pertenceAoUsuario(owner)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		product.associaImagens(links);
		manager.merge(product);

		return product.toString();
		
	}


}
