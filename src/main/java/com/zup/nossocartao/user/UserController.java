package com.zup.nossocartao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UserController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AvoidDuplicateEmailValidator avoidDuplicateEmailValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(avoidDuplicateEmailValidator);
    }

    @PostMapping("/usuarios")
    @Transactional
    public String cria(@RequestBody @Valid NewUserRequest request) {
        Users newUsers = request.toUser();
        manager.persist(newUsers);
        return newUsers.toString();
    }
}
