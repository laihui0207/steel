package com.huivip.steel.service.impl;

import com.huivip.steel.dao.NewsDao;
import com.huivip.steel.dao.NewsTypeDao;
import com.huivip.steel.model.News;
import com.huivip.steel.model.NewsType;
import com.huivip.steel.service.NewsManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.jws.WebService;

@Service("newsManager")
@WebService(serviceName = "NewsService", endpointInterface = "com.huivip.steel.service.NewsManager")
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
    NewsDao newsDao;
    @Autowired
    NewsTypeDao newsTypeDao;

    @Autowired
    public NewsManagerImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
    }

    @Override
    public List<News> newsList() {
        return newsDao.getAll();
    }

    @Override
    public List<News> query(@RequestParam("q") String q) {
        return this.search(q, News.class);
    }

    @Override
    public List<News> newsListOfNewsType(String newsTypeId) {
        NewsType newsType=newsTypeDao.get(Long.parseLong(newsTypeId));
        return newsDao.newsListOfNewsType(newsType);
    }

    @Override
    public void deleteNews(String id) {

    }
}