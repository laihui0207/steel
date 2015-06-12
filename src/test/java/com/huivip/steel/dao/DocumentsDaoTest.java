package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.Documents;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentsDaoTest extends BaseDaoTestCase {
    @Autowired
    private DocumentsDao documentsDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDocuments() {
        Documents documents = new Documents();

        // enter all required fields
        documents.setDocSize(513765452);
        documents.setViewLimit(Boolean.FALSE);

        log.debug("adding documents...");
        documents = documentsDao.save(documents);

        documents = documentsDao.get(documents.getId());

        assertNotNull(documents.getId());

        log.debug("removing documents...");

        documentsDao.remove(documents.getId());

        // should throw DataAccessException 
        documentsDao.get(documents.getId());
    }
}