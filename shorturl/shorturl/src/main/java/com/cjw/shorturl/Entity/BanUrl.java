package com.cjw.shorturl.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BanUrl {
    @Id
    @GeneratedValue
    @Column(name="ban_url_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createdAt;
}
