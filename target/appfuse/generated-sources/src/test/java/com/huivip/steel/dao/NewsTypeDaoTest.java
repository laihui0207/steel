package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.NewsType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private NewsTypeDao newsTypeDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveNewsType() {
        NewsType newsType = new NewsType();

        // enter all required fields

        log.debug("adding newsType...");
        newsType = newsTypeDao.save(newsType);

        newsType = newsTypeDao.get(newsType.getId());

        assertNotNull(newsType.getId());

        log.debug("removing newsType...");

        newsTypeDao.remove(newsType.getId());

        // should throw DataAccessException 
        newsTypeDao.get(newsType.getId());
    }
}