package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.News;
import com.huivip.steel.dao.NewsDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("newsDao")
public class NewsDaoHibernate extends GenericDaoHibernate<News, Long> implements NewsDao {

    public NewsDaoHibernate() {
        super(News.class);
    }
}
