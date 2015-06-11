package com.huivip.steel.service.impl;

import com.huivip.steel.dao.MessageDao;
import com.huivip.steel.model.Message;
import com.huivip.steel.service.MessageManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("messageManager")
@WebService(serviceName = "MessageService", endpointInterface = "com.huivip.steel.service.MessageManager")
public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager {
    MessageDao messageDao;

    @Autowired
    public MessageManagerImpl(MessageDao messageDao) {
        super(messageDao);
        this.messageDao = messageDao;
    }
}