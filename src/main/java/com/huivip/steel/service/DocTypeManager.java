package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.DocType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DocTypeManager extends GenericManager<DocType, Long> {
    
}