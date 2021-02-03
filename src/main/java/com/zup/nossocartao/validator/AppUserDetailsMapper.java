package com.zup.nossocartao.validator;

import com.zup.nossocartao.validator.security.UserDetailsMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new LoggedUser((User) shouldBeASystemUser);
    }


}
