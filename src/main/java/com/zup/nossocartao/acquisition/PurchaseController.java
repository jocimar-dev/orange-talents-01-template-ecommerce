package com.zup.nossocartao.acquisition;

import com.zup.nossocartao.questions.Emails;
import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.user.UserRepository;
import com.zup.nossocartao.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PurchaseController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Emails emails;

	@PostMapping(value = "/api/compras")
	@Transactional
	public String cria(@RequestBody @Valid NewPurchaseRequest request,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

		Product productPurchased = manager.find(Product.class,
				request.getIdProduct());

		int quantity = request.getQuantity();
		boolean abateu = productPurchased.decreasesStock(quantity);


		if (abateu) {
			Users comprador = userRepository
					.findByEmail("zupacademy@zup.com,br").get();
			PayGateway gateway = request.getGateway();
			Purchase newPurchase = new Purchase(productPurchased, quantity,
					comprador, gateway);
			manager.persist(newPurchase);
			emails.novaCompra(newPurchase);
			
			return newPurchase.urlRedirect(uriComponentsBuilder);
									
		}

		BindException troubleStock = new BindException(request,
				"novaCompraRequest");
		troubleStock.reject(null,
				"Compra não foi realizada por falta de estoque");

		throw troubleStock;

	}

}
