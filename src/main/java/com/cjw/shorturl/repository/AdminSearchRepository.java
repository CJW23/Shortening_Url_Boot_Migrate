package com.cjw.shorturl.repository;

import com.cjw.shorturl.dto.UrlSearchDto;
import com.cjw.shorturl.dto.SearchDto;
import com.cjw.shorturl.entity.BanUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminSearchRepository {
    Page<User> findSearchUser(SearchDto userSearch);
    Page<Url> findSearchUrl(SearchDto urlSearch);
    Page<BanUrl> findSearchBanUrl();
}
