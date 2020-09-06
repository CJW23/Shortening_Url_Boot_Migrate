package com.cjw.shorturl.entity;

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
    @GeneratedValue
    @Column(name = "url_id")
    private Long id;
    private String shortUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private String nameUrl;

    @Column(columnDefinition = "bigint default 0")
    private Long count;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @OneToMany(mappedBy = "url")
    private List<AccessUrl> accessUrls = new ArrayList<>();
}
