package com.zup.nossocartao.productsdetails;

import com.zup.nossocartao.registerproduct.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class ProductsDetailsController {

	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public ProductsDetailsView getMethodName(@PathVariable("id") Long id) {
		Product chosenProduct = manager.find(Product.class, id);
		return new ProductsDetailsView(chosenProduct);
	}

}
