package com.zup.nossocartao.registerproduct;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class NotAllowFeatureWithDuplicateName implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NewProductRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		NewProductRequest request = (NewProductRequest) target;
		Set<String> nomesIguais = request.buscaCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais "+nomesIguais);
		}
	}

}
