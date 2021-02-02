package com.zup.nossocartao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class AvoidDuplicateEmailValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewUserRequest request = (NewUserRequest) target;
        Optional<User> insertionAttempt = userRepository.findByEmail(request.getEmail());
        if(insertionAttempt.isPresent()) {
            errors.rejectValue("email",null, "Email já encontra-se cadastrado no sistema");
        }
    }

}
