package com.cjw.shorturl.service.manager;

import com.cjw.shorturl.exception.MakeRandomException;
import com.cjw.shorturl.respository.UrlRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * URL 관련 로직 클래스
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UrlManager {
    final UrlRepositoryImpl urlRepository;

    /**
     * URL 정규식 검사
     * @param url
     * @return
     */
    public boolean checkUrlRegex(String url) {
        //url 도메인 검사
        Pattern pattern = Pattern.compile("(http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:[0-9]+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?");
        Matcher match = pattern.matcher(url);
        return match.matches();
    }

    /**
     * 아이디 랜덤값 생성
     * @return
     * @throws Exception
     */
    public int makeRandom() throws MakeRandomException {
        int maxCount = 3;
        int count = 0;
        int randomNum = 4;
        while (count < maxCount) {
            randomNum = (int) (Math.random() * 100000000) + 1000000;
            if (urlRepository.findUrl((long) randomNum) == null) {
                return randomNum;
            }
            count++;
        }
        throw new MakeRandomException("랜덤값 오류");
    }

    /**
     * URL 유효성 검증
     * @param checkUrl
     * @return
     */
    public boolean checkExistUrl(String checkUrl) {
        boolean check = false;
        try{
            URL url = new URL(checkUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() >= 200 && connection.getResponseCode() < 400){
                check = true;
            }
        } catch (Exception e) {
            log.info("checkExistUrl::" + e.getMessage());
            return false;
        }
        return check;
    }

    public boolean checkAlreadyUrl(Long id, String checkUrl){
        return urlRepository.findAlreadyUrl(id, checkUrl);
    }
}
