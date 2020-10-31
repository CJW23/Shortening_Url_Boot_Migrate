package com.cjw.shorturl.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;

/**
 * 유저의 인증 관리
 */
@Getter
public class MyUserDetails implements UserDetails {
	private final Long id;
	private final String email;
	private final String name;
	private final String nickname;
	private final String password;
	private final String phone;
	private final String role;

	public MyUserDetails(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.role = "ROLE_" + user.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.role));
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
