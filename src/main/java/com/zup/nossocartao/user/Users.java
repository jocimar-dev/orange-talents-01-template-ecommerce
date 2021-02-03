package com.zup.nossocartao.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6)
    private String password;

    @PastOrPresent
    private LocalDateTime creationTime;

    @Deprecated
    public Users() {

    }

    public Users(@NotBlank @Email String email,
                 @NotBlank @Length(min = 6) CleanPassword cleanPassword) {
        Assert.isTrue(StringUtils.hasLength(email),"Não pode ser cadastrado email em branco");
        Assert.notNull(cleanPassword,"Não pode ser cadatrado senha em branco");
        this.email = email;
        this.password = cleanPassword.hash();
        this.creationTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) &&
                email.equals(users.email) &&
                password.equals(users.password) &&
                creationTime.equals(users.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), email, password, creationTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Users.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("crationTime=" + creationTime)
                .toString();
    }

    public Long getId() {
        return id;
    }
    public String getSenha() {
        return password;
    }

    public String getEmail() {
        return this.email;
    }

}
