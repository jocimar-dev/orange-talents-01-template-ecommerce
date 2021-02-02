package com.zup.nossocartao.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6)
    private String password;

    public NewUserRequest(@Email @NotBlank String email,
                          @NotBlank @Length(min = 6) String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public User toUser() {
        return new User(email, new CleanPassword(password));
    }
}
