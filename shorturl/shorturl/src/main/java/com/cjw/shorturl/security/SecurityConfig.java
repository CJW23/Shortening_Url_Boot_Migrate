package com.cjw.shorturl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cjw.shorturl.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final LoginServiceImpl LoginServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/home").hasRole("USER")
			.antMatchers("/user/signUp").permitAll()
			.and()
			.formLogin().defaultSuccessUrl("/user/home")
			.and()
			.csrf().disable()
			//403 forbidden 처리 페이지
			.exceptionHandling().accessDeniedPage("/user/denied");
			//.defaultSuccessUrl("/home")
	}

	/**
	 * spring security의 모든 인증은 AuthenticationManager 통해 처리
	 * @param auth
	 * @throws Exception
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(LoginServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
}
