package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.PostType;
import com.huivip.steel.dao.PostTypeDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("postTypeDao")
public class PostTypeDaoHibernate extends GenericDaoHibernate<PostType, Long> implements PostTypeDao {

    public PostTypeDaoHibernate() {
        super(PostType.class);
    }
}
