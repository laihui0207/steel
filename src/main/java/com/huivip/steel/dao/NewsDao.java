package com.huivip.steel.dao;

import com.huivip.steel.model.News;
import com.huivip.steel.model.NewsType;

import java.util.List;

/**
 * An interface that provides a data management interface to the News table.
 */
public interface NewsDao extends GenericDao<News, Long> {

    List<News> newsListOfNewsType(NewsType newsType);
}