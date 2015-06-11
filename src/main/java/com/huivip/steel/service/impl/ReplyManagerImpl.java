package com.huivip.steel.service.impl;

import com.huivip.steel.dao.ReplyDao;
import com.huivip.steel.model.Reply;
import com.huivip.steel.service.ReplyManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("replyManager")
@WebService(serviceName = "ReplyService", endpointInterface = "com.huivip.steel.service.ReplyManager")
public class ReplyManagerImpl extends GenericManagerImpl<Reply, Long> implements ReplyManager {
    ReplyDao replyDao;

    @Autowired
    public ReplyManagerImpl(ReplyDao replyDao) {
        super(replyDao);
        this.replyDao = replyDao;
    }

    @Override
    public List<Reply> repliesOfPost(String postID) {
        return replyDao.repliesOfPost(postID);
    }
}