package com.cjw.shorturl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@DynamicInsert
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
