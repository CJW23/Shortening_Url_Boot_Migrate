package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.lib.Base62;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MainServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    MainService mainService;
    @Autowired
    UrlManager urlManager;
    @Autowired
    UrlRepository urlRepository;

    @Test
    @Rollback(false)
    public void makeUrlTest() throws Exception {
        Url url = new Url();
        url.setOriginalUrl("http://naver.com");
        url.setNameUrl("네이버");
        Url testUrl = mainService.makeUrl(url);
        Assert.assertEquals("인코딩id 다름", Base62.decode("lJhGK"), 149320775);
        Assert.assertEquals("awdwad", url, testUrl);
    }

    @Test
    public void 정규식테스트() throws Exception {
        assertTrue(urlManager.checkUrlRegex("https://naver.com"));
    }

    @Test
    public void 회원칮기() throws Exception {

    }
}
