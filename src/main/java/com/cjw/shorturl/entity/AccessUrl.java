package com.cjw.shorturl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@DynamicInsert
public class AccessUrl {
    @Id
    @GeneratedValue
    @Column(name="access_url_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "url_id")
    private Url url;

    private String beforeUrl;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date accessTime;
}
