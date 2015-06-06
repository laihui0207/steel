package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.Post;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostDaoTest extends BaseDaoTestCase {
    @Autowired
    private PostDao postDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePost() {
        Post post = new Post();

        // enter all required fields
        post.setIfAccessAllReply(Boolean.FALSE);

        log.debug("adding post...");
        post = postDao.save(post);

        post = postDao.get(post.getId());

        assertNotNull(post.getId());

        log.debug("removing post...");

        postDao.remove(post.getId());

        // should throw DataAccessException 
        postDao.get(post.getId());
    }
}