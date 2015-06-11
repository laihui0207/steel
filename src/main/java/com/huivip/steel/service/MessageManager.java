package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.Message;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MessageManager extends GenericManager<Message, Long> {
    
}