package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.Message;
import com.huivip.steel.dao.MessageDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("messageDao")
public class MessageDaoHibernate extends GenericDaoHibernate<Message, Long> implements MessageDao {

    public MessageDaoHibernate() {
        super(Message.class);
    }
}
