package com.cjw.shorturl.entity;

import com.cjw.shorturl.dto.UrlDetailResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Url {
    @Id
    @Column(name = "url_id")
    private Long id;
    private String shortUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String originalUrl;

    private String nameUrl;

    @Column(columnDefinition = "bigint default 0")
    private Long count;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @OneToMany(mappedBy = "url")
    private List<AccessUrl> accessUrls = new ArrayList<>();

    public void addAccessUrl(AccessUrl accessUrl){
        accessUrls.add(accessUrl);
        accessUrl.setUrl(this);
    }

    public int countAccessUrl(){
        return accessUrls.size();
    }

    public static UrlDetailResponse makeDetailUrl(Url url){
        UrlDetailResponse response = new UrlDetailResponse();
        response.setId(url.getId());
        response.setCount(url.getCount());
        response.setCreatedAt(url.getCreatedAt().toString());
        if(url.getNameUrl() == null){
            response.setNameUrl(url.getOriginalUrl());
        } else {
            response.setNameUrl(url.getNameUrl());
        }
        response.setOriginalUrl(url.getOriginalUrl());
        response.setShortUrl(url.getShortUrl());
        return response;
    }
}
