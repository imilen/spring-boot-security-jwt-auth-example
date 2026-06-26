package com.spring_boot_security_jwt_auth_example.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring_boot_security_jwt_auth_example.entity.User;

public class CustomUserDetails implements UserDetails {

	private final User user;
	private final Set<GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.user = user;
		this.authorities = buildAuthorities();
	}

	private static Set<GrantedAuthority> buildAuthorities() {
		return new HashSet<>();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

}
