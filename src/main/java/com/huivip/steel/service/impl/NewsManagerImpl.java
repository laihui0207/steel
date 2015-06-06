package com.huivip.steel.service.impl;

import com.huivip.steel.dao.NewsDao;
import com.huivip.steel.model.News;
import com.huivip.steel.service.NewsManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("newsManager")
@WebService(serviceName = "NewsService", endpointInterface = "com.huivip.steel.service.NewsManager")
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
    NewsDao newsDao;

    @Autowired
    public NewsManagerImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
    }
}