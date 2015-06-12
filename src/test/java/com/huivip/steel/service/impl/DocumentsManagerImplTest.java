package com.huivip.steel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.steel.dao.DocumentsDao;
import com.huivip.steel.model.Documents;
import com.huivip.steel.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DocumentsManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DocumentsManagerImpl manager;

    @Mock
    private DocumentsDao dao;

    @Test
    public void testGetDocuments() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Documents documents = new Documents();
        given(dao.get(id)).willReturn(documents);

        //when
        Documents result = manager.get(id);

        //then
        assertSame(documents, result);
    }

    @Test
    public void testGetDocumentss() {
        log.debug("testing getAll...");
        //given
        final List<Documents> documentss = new ArrayList<>();
        given(dao.getAll()).willReturn(documentss);

        //when
        List result = manager.getAll();

        //then
        assertSame(documentss, result);
    }

    @Test
    public void testSaveDocuments() {
        log.debug("testing save...");

        //given
        final Documents documents = new Documents();
        // enter all required fields
        documents.setDocSize(503443510);
        documents.setViewLimit(Boolean.FALSE);

        given(dao.save(documents)).willReturn(documents);

        //when
        manager.save(documents);

        //then
        verify(dao).save(documents);
    }

    @Test
    public void testRemoveDocuments() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}
