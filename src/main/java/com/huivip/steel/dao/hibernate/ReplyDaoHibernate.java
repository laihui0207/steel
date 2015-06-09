package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.Reply;
import com.huivip.steel.dao.ReplyDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("replyDao")
public class ReplyDaoHibernate extends GenericDaoHibernate<Reply, Long> implements ReplyDao {

    public ReplyDaoHibernate() {
        super(Reply.class);
    }
}
