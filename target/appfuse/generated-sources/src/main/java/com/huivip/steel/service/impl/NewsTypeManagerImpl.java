package com.huivip.steel.service.impl;

import com.huivip.steel.dao.NewsTypeDao;
import com.huivip.steel.model.NewsType;
import com.huivip.steel.service.NewsTypeManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("newsTypeManager")
@WebService(serviceName = "NewsTypeService", endpointInterface = "com.huivip.steel.service.NewsTypeManager")
public class NewsTypeManagerImpl extends GenericManagerImpl<NewsType, Long> implements NewsTypeManager {
    NewsTypeDao newsTypeDao;

    @Autowired
    public NewsTypeManagerImpl(NewsTypeDao newsTypeDao) {
        super(newsTypeDao);
        this.newsTypeDao = newsTypeDao;
    }
}