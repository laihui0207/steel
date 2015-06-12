package com.huivip.steel.service.impl;

import com.huivip.steel.dao.DocTypeDao;
import com.huivip.steel.model.DocType;
import com.huivip.steel.service.DocTypeManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("docTypeManager")
@WebService(serviceName = "DocTypeService", endpointInterface = "com.huivip.steel.service.DocTypeManager")
public class DocTypeManagerImpl extends GenericManagerImpl<DocType, Long> implements DocTypeManager {
    DocTypeDao docTypeDao;

    @Autowired
    public DocTypeManagerImpl(DocTypeDao docTypeDao) {
        super(docTypeDao);
        this.docTypeDao = docTypeDao;
    }
}