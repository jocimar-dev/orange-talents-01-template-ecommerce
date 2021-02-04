package com.zup.nossocartao.questions;

import com.zup.nossocartao.acquisition.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails {
	
	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Question question) {
		mailer.send("<html>...</html>","Nova pergunta...", question.getInterested().getEmail(),"newquestion@zup.com.br", question.getProductOwner().getEmail());
	}

	public void novaCompra(Purchase newPurchase) {
		mailer.send("nova compra..." + newPurchase, "Você tem uma nova compra",
				newPurchase.getBuyer().getEmail(),
				"compras@nossomercadolivre.com",
				newPurchase.getProductOwner().getEmail());
	}

	
}
