package com.cjw.shorturl.entity;

import com.cjw.shorturl.controller.SignUpDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "User")
@Getter
@Setter
@Slf4j
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phone;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(columnDefinition = "VARCHAR(5) DEFAULT 'USER'")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Url> urls = new ArrayList<>();

    public void addUrl(Url url){
        urls.add(url);
        url.setUser(this);
    }


    public static User makeSignUpUser(SignUpDTO info){
        User user = new User();
        user.setEmail(info.getEmail());
        user.setName(info.getName());
        user.setNickname(info.getNickname());
        user.setPassword(info.getPassword());
        user.setPhone(info.getPhone());
        return user;
    }
}
