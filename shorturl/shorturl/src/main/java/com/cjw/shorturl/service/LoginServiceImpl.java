package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;
import com.cjw.shorturl.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements UserDetailsService {
	private final UserRepositoryImpl userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email);
		return new MyUserDetails(user);
	}
}
