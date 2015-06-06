package com.huivip.steel.service.impl;

import com.huivip.steel.dao.PostDao;
import com.huivip.steel.model.Post;
import com.huivip.steel.service.PostManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("postManager")
@WebService(serviceName = "PostService", endpointInterface = "com.huivip.steel.service.PostManager")
public class PostManagerImpl extends GenericManagerImpl<Post, Long> implements PostManager {
    PostDao postDao;

    @Autowired
    public PostManagerImpl(PostDao postDao) {
        super(postDao);
        this.postDao = postDao;
    }
}