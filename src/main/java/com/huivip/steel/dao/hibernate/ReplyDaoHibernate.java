package com.huivip.steel.dao.hibernate;

import com.huivip.steel.dao.ReplyDao;
import com.huivip.steel.model.Reply;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("replyDao")
public class ReplyDaoHibernate extends GenericDaoHibernate<Reply, Long> implements ReplyDao {

    public ReplyDaoHibernate() {
        super(Reply.class);
    }

    @Override
    public List<Reply> repliesOfPost(String postID) {
        String queryString="From Reply where post.id="+postID;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}
