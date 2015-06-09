package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.Reply;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ReplyManager extends GenericManager<Reply, Long> {
    
}