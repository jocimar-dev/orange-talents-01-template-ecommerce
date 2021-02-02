package com.zup.nossocartao.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class CleanPassword {

    private String password;

    public CleanPassword(@NotBlank @Length(min = 6) String password) {
        Assert.hasLength(password, "Favor preencher o campo senha");
        Assert.isTrue(password.length()>=6,"Mínimo 6 caracteres");

        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
