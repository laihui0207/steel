package com.huivip.steel.service.impl;

import com.huivip.steel.dao.PostTypeDao;
import com.huivip.steel.model.PostType;
import com.huivip.steel.service.PostTypeManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("postTypeManager")
@WebService(serviceName = "PostTypeService", endpointInterface = "com.huivip.steel.service.PostTypeManager")
public class PostTypeManagerImpl extends GenericManagerImpl<PostType, Long> implements PostTypeManager {
    PostTypeDao postTypeDao;

    @Autowired
    public PostTypeManagerImpl(PostTypeDao postTypeDao) {
        super(postTypeDao);
        this.postTypeDao = postTypeDao;
    }
}