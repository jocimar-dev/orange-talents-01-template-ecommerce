package com.zup.nossocartao.acquisition;

import com.zup.nossocartao.registerproduct.Product;
import com.zup.nossocartao.user.Users;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Product chosenProduct;
	@Positive
	private int quantity;
	@ManyToOne
	@NotNull
	@Valid
	private Users buyer;
	@Enumerated
	@NotNull
	private PayGateway payGateway;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transaction> transactions = new HashSet<>();
	
	@Deprecated
	public Purchase() {

	}

	public Purchase(@NotNull @Valid Product productBuy,
					@Positive int quantity, @NotNull @Valid Users buyer,
					PayGateway payGateway) {
		this.chosenProduct = productBuy;
		this.quantity = quantity;
		this.buyer = buyer;
		this.payGateway = payGateway;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Purchase.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("chosenProduct=" + chosenProduct)
				.add("quantity=" + quantity)
				.add("buyer=" + buyer)
				.add("payGateway=" + payGateway)
				.add("transactions=" + transactions)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public String urlRedirect(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.payGateway.urlFeedback(this, uriComponentsBuilder);
	}

	public Users getBuyer() {
		return buyer;
	}

	public Users getProductOwner() {
		return chosenProduct.getOwner();
	}

	public void addTransaction(@Valid PayFeedbackGateway request) {
		Transaction newTransaction = request.toTransaction(this);
		Assert.state(!this.transactions.contains(newTransaction),
				"Esta transação já foi processada "
						+ newTransaction);
		Assert.state(successfullyConcluded().isEmpty(),
				"Esse compra já foi concluída com sucesso");
		this.transactions.add(newTransaction);
	}

	private Set<Transaction> successfullyConcluded() {
		Set<Transaction> successfullyConcluded = this.transactions.stream()
				.filter(Transaction::concluded)
				.collect(Collectors.toSet());
				Assert.isTrue(successfullyConcluded.size() <= 1,
						"Cuidado, foram processadas mais de uma transação "+this.id);
		
		return successfullyConcluded;
	}

	public boolean processed() {
		return !successfullyConcluded().isEmpty();
	}

}
