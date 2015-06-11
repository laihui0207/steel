package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.Message;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageDaoTest extends BaseDaoTestCase {
    @Autowired
    private MessageDao messageDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveMessage() {
        Message message = new Message();

        // enter all required fields
        message.setStatus(Boolean.FALSE);

        log.debug("adding message...");
        message = messageDao.save(message);

        message = messageDao.get(message.getId());

        assertNotNull(message.getId());

        log.debug("removing message...");

        messageDao.remove(message.getId());

        // should throw DataAccessException 
        messageDao.get(message.getId());
    }
}