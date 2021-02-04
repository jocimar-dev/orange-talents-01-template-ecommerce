package com.zup.nossocartao.productsdetails;

import com.zup.nossocartao.questions.Question;
import com.zup.nossocartao.registerproduct.Opinions;
import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.registerproduct.ProductImage;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ProductsDetailsView {

	private String description;
	private String name;
	private BigDecimal preco;
	private Set<ProductsDetailsFeatures> features;
	private Set<String> imagesLinks;
	private SortedSet<String> questions;
	private Set<Map<String,String>> opinions;
	private double notesMedia;
	private int total;

	public ProductsDetailsView(Product product) {
		this.description = product.getDescription();
		this.name = product.getName();
		this.preco = product.getValue();

		this.features = product
				.mapFeatures(ProductsDetailsFeatures::new);
		this.imagesLinks = product.mapImages(ProductImage::getLink);
		this.questions = product.mapQuestions(Question::getTitulo);
		

		Opinions opinioes = product.getOpinions();
		this.opinions = opinioes.mapOpinions(opiniao -> {
			return Map.of("titulo",opiniao.getTitle(),"descricao",opiniao.getDescricao());
		});
		

		this.notesMedia = opinioes.media();
		this.total = opinioes.total();
				
		

	}
	
	public int getTotal() {
		return total;
	}
	
	public double getNotesMedia() {
		return notesMedia;
	}
	
	public Set<Map<String, String>> getOpinions() {
		return opinions;
	}
	
	public SortedSet<String> getQuestions() {
		return questions;
	}
	
	public Set<String> getImagesLinks() {
		return imagesLinks;
	}
	
	public Set<ProductsDetailsFeatures> getFeatures() {
		return features;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
