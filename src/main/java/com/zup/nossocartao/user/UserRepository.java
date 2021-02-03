package com.zup.nossocartao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findByEmail(@Email String email);
}