package com.zup.nossocartao.validator;

import com.zup.nossocartao.user.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class LoggedUser implements UserDetails {

	private Users users;
	private User springUserDetails;

	public LoggedUser(@NotNull @Valid User user) {
		this.users = users;
		springUserDetails = new User(user.getUsername(), user.getPassword(), List.of());
	}
	
	

	public Collection<GrantedAuthority> getAuthorities() {
		return springUserDetails.getAuthorities();
	}



	public String getPassword() {
		return springUserDetails.getPassword();
	}



	public String getUsername() {
		return springUserDetails.getUsername();
	}



	public boolean isEnabled() {
		return springUserDetails.isEnabled();
	}



	public boolean isAccountNonExpired() {
		return springUserDetails.isAccountNonExpired();
	}



	public boolean isAccountNonLocked() {
		return springUserDetails.isAccountNonLocked();
	}



	public boolean isCredentialsNonExpired() {
		return springUserDetails.isCredentialsNonExpired();
	}
}
