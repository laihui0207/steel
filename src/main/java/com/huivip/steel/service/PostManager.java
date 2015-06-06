package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.Post;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PostManager extends GenericManager<Post, Long> {
    
}