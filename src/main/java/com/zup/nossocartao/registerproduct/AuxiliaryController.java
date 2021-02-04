package com.zup.nossocartao.registerproduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class AuxiliaryController {
	@PersistenceContext 
	private EntityManager manager;

	@GetMapping(value = "/todos-produtos")
	public String lista() {
		return manager.createQuery("select p from Product p").getResultList().toString();
	}
	
	@GetMapping(value = "/todos-usuarios")
	public String listaUsuarios() {
		return manager.createQuery("select u from Usuario u").getResultList().toString();
	}

}
