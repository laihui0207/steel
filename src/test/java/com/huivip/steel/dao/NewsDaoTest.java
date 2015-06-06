package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.News;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsDaoTest extends BaseDaoTestCase {
    @Autowired
    private NewsDao newsDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveNews() {
        News news = new News();

        // enter all required fields
        news.setIfAccessLimited(Boolean.FALSE);

        log.debug("adding news...");
        news = newsDao.save(news);

        news = newsDao.get(news.getId());

        assertNotNull(news.getId());

        log.debug("removing news...");

        newsDao.remove(news.getId());

        // should throw DataAccessException 
        newsDao.get(news.getId());
    }
}