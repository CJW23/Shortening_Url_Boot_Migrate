package com.cjw.shorturl.respository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoginRepositoryImpl {

	private final EntityManager em;

	public User findUserByEmail(String email) {
		TypedQuery<User> query = em.createQuery("select m from User as m where m.email = ?1", User.class)
			.setParameter(1, email);
		return query.getSingleResult();
	}

	public Long saveUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		em.persist(user);
		return user.getId();
	}
}
