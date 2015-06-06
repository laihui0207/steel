package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.Post;
import com.huivip.steel.dao.PostDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("postDao")
public class PostDaoHibernate extends GenericDaoHibernate<Post, Long> implements PostDao {

    public PostDaoHibernate() {
        super(Post.class);
    }
}
