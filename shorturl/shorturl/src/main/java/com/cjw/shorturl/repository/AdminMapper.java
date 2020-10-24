package com.cjw.shorturl.repository;

import com.cjw.shorturl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<User> findUserBySearch() throws Exception;
}
