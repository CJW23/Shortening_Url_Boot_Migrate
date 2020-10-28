package com.cjw.shorturl.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BasicTest {
    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        User user = new User();
        user.setName("test1");
        user.setEmail("awdawd");
        user.setNickname("xcxc");
        user.setPassword("zsc");
        user.setPhone("123123");

        em.persist(user);
        assertEquals(user, em.find(User.class, user.getId()));
    }
}
