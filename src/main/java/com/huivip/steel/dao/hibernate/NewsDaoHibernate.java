package com.huivip.steel.dao.hibernate;

import com.huivip.steel.dao.NewsDao;
import com.huivip.steel.model.News;
import com.huivip.steel.model.NewsType;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDao")
public class NewsDaoHibernate extends GenericDaoHibernate<News, Long> implements NewsDao {

    public NewsDaoHibernate() {
        super(News.class);
    }

    @Override
    public List<News> newsListOfNewsType(NewsType newsType) {
        String queryString="from News where newsType.id="+newsType.getId();
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}
