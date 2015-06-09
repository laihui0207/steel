package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.PostType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PostTypeManager extends GenericManager<PostType, Long> {
    
}