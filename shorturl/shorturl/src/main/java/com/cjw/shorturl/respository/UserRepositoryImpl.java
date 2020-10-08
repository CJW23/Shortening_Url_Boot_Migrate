package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl{
	private final EntityManager em;

	public User findUserById(Long id){
		return em.find(User.class, id);
	}
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

	public boolean findExistNickname(String nickname){
		TypedQuery<User> query = em.createQuery("select m from User as m where m.nickname = ?1", User.class)
				.setParameter(1, nickname);
		return query.getResultList().size() < 1;
	}

	public void removeUser(User user){
		em.remove(user);
	}
}
