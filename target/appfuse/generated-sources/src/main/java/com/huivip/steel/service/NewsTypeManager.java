package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.NewsType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface NewsTypeManager extends GenericManager<NewsType, Long> {
    
}