package com.huivip.steel.service;

import com.huivip.steel.model.NewsType;

import javax.jws.WebService;
import javax.ws.rs.Path;

@WebService
@Path("/newsTypes")
public interface NewsTypeManager extends GenericManager<NewsType, Long> {
    
}