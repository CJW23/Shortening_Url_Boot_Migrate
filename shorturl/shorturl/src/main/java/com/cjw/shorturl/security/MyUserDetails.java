package com.cjw.shorturl.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;

/**
 * 유저의 인증 관리
 */
@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {
	private String email;
	private String password;
	private String auth;

	public MyUserDetails(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.auth = "ROLE_" + user.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
