package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.News;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface NewsManager extends GenericManager<News, Long> {
    
}