package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.service.manager.UrlManager;
import groovy.util.logging.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.mysql.cj.conf.PropertyKey.*;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MainServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
	UrlManager urlManager;

    @Test
    @Rollback(false)
    public void makeUrlTest() throws Exception {
        Url url = new Url();
        url.setOriginalUrl("http://naver.com");
        url.setNameUrl("네이버");
        Assert.assertEquals("인코딩id 다름", Base62.decode("lJhGK"), 149320775);
    }

    @Test
    public void 정규식테스트() throws Exception {
        assertTrue(urlManager.checkUrlRegex("https://naver.com"));
    }

    @Test
    public void 회원칮기() throws Exception {

    }

    @Test
    public void 유저데이터출력() throws Exception{
        User user = em.find(User.class, 1);

    }
}
