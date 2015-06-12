package com.huivip.steel.service.impl;

import com.huivip.steel.dao.DocumentsDao;
import com.huivip.steel.model.Documents;
import com.huivip.steel.service.DocumentsManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("documentsManager")
@WebService(serviceName = "DocumentsService", endpointInterface = "com.huivip.steel.service.DocumentsManager")
public class DocumentsManagerImpl extends GenericManagerImpl<Documents, Long> implements DocumentsManager {
    DocumentsDao documentsDao;

    @Autowired
    public DocumentsManagerImpl(DocumentsDao documentsDao) {
        super(documentsDao);
        this.documentsDao = documentsDao;
    }
}