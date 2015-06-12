package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.Documents;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DocumentsManager extends GenericManager<Documents, Long> {
    
}