package com.zup.nossocartao.productsdetails;

import com.zup.nossocartao.registerproduct.ProductFeature;

public class ProductsDetailsFeatures {

	private String name;
	private String description;

	public ProductsDetailsFeatures(ProductFeature feature) {
		this.name = feature.getName();
		this.description = feature.getDescription();
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

}
