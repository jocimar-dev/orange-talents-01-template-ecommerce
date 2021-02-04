package com.zup.nossocartao.registerproduct;

import com.zup.nossocartao.category.Category;
import com.zup.nossocartao.opinion.Opinion;
import com.zup.nossocartao.questions.Question;
import com.zup.nossocartao.user.Users;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@Positive
	private int quantity;

	@NotBlank
	@Length(max = 1000)
	private String description;

	@NotNull
	@Positive
	private BigDecimal value;

	@NotNull
	@Valid
	@ManyToOne
	private Category category;

	@NotNull
	@Valid
	@ManyToOne
	private Users owner;

	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	private Set<ProductFeature> features = new HashSet<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	private Set<ProductImage> images = new HashSet<>();

	@OneToMany(mappedBy = "product")
	@OrderBy("title asc")
	private SortedSet<Question> questions = new TreeSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opinion> opinion = new HashSet<>();

	@Deprecated
	public Product() {

	}

	public Product(@NotBlank String name, @Positive int quantity,
				   @NotBlank @Length(max = 1000) String description,
				   @NotNull @Positive BigDecimal value,
				   @NotNull @Valid Category category, @NotNull @Valid Users owner,
				   @Size(min = 3) @Valid Collection<NewFeaturesRequests> features) {

		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.value = value;
		this.category = category;
		this.owner = owner;
		this.features.addAll(features.stream()
				.map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));

		Assert.isTrue(this.features.size() >= 3,
				"Todo produto precisa ter no mínimo 3 ou mais características");

	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name='" + name + "'")
				.add("quantity=" + quantity)
				.add("description='" + description + "'")
				.add("value=" + value)
				.add("category=" + category)
				.add("owner=" + owner)
				.add("features=" + features)
				.add("images=" + images)
				.add("questions=" + questions)
				.add("opinion=" + opinion)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(value, product.value) && Objects.equals(category, product.category) && Objects.equals(getOwner(), product.getOwner()) &&
				Objects.equals(features, product.features) &&
				Objects.equals(images, product.images) &&
				Objects.equals(questions, product.questions) &&
				Objects.equals(opinion, product.opinion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, quantity, description, value, category, getOwner(), features, images, questions, opinion);
	}

	public void associaImagens(Set<String> links) {
		Set<ProductImage> imagens = links.stream()
				.map(link -> new ProductImage(this, link))
				.collect(Collectors.toSet());

		this.images.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Users possivelDono) {
		return this.owner.equals(possivelDono);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Users getOwner() {
		return owner;
	}

	public <T> Set<T> mapFeatures(
			Function<ProductFeature, T> mapFunctions) {
		return this.features.stream().map(mapFunctions)
				.collect(Collectors.toSet());
	}

	public <T> Set<T> mapImages(Function<ProductImage, T> mapFunctions) {
		return this.images.stream().map(mapFunctions)
				.collect(Collectors.toSet());
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapQuestions(Function<Question, T> mapFunctions) {
		return this.questions.stream().map(mapFunctions)
				.collect(Collectors.toCollection(TreeSet :: new));
	}

	public Opinions getOpinions() {
		return new Opinions(this.opinion);
	}

	public boolean decreasesStock(@Positive int quantity) {
		Assert.isTrue(quantity > 0, "A quantidade deve ser maior que zero para abater o estoque "+quantity);
		
		if(quantity <= this.quantity) {
			this.quantity-=quantity;
			return true;
			
		}
		
		return false;
	}
}
