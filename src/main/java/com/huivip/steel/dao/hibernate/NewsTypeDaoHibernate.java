package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.NewsType;
import com.huivip.steel.dao.NewsTypeDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("newsTypeDao")
public class NewsTypeDaoHibernate extends GenericDaoHibernate<NewsType, Long> implements NewsTypeDao {

    public NewsTypeDaoHibernate() {
        super(NewsType.class);
    }
}
