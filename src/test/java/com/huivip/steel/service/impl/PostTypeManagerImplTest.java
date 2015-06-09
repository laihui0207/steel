package com.huivip.steel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.steel.dao.PostTypeDao;
import com.huivip.steel.model.PostType;
import com.huivip.steel.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class PostTypeManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private PostTypeManagerImpl manager;

    @Mock
    private PostTypeDao dao;

    @Test
    public void testGetPostType() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final PostType postType = new PostType();
        given(dao.get(id)).willReturn(postType);

        //when
        PostType result = manager.get(id);

        //then
        assertSame(postType, result);
    }

    @Test
    public void testGetPostTypes() {
        log.debug("testing getAll...");
        //given
        final List<PostType> postTypes = new ArrayList<>();
        given(dao.getAll()).willReturn(postTypes);

        //when
        List result = manager.getAll();

        //then
        assertSame(postTypes, result);
    }

    @Test
    public void testSavePostType() {
        log.debug("testing save...");

        //given
        final PostType postType = new PostType();
        // enter all required fields

        given(dao.save(postType)).willReturn(postType);

        //when
        manager.save(postType);

        //then
        verify(dao).save(postType);
    }

    @Test
    public void testRemovePostType() {
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
