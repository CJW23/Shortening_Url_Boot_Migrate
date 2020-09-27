package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl{
	private final EntityManager em;

}
