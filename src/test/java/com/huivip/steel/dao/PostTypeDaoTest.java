package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.PostType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private PostTypeDao postTypeDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePostType() {
        PostType postType = new PostType();

        // enter all required fields

        log.debug("adding postType...");
        postType = postTypeDao.save(postType);

        postType = postTypeDao.get(postType.getId());

        assertNotNull(postType.getId());

        log.debug("removing postType...");

        postTypeDao.remove(postType.getId());

        // should throw DataAccessException 
        postTypeDao.get(postType.getId());
    }
}