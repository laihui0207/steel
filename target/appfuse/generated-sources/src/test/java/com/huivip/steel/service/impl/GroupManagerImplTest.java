package com.huivip.steel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.steel.dao.GroupDao;
import com.huivip.steel.model.Group;
import com.huivip.steel.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class GroupManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private GroupManagerImpl manager;

    @Mock
    private GroupDao dao;

    @Test
    public void testGetGroup() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Group group = new Group();
        given(dao.get(id)).willReturn(group);

        //when
        Group result = manager.get(id);

        //then
        assertSame(group, result);
    }

    @Test
    public void testGetGroups() {
        log.debug("testing getAll...");
        //given
        final List<Group> groups = new ArrayList<>();
        given(dao.getAll()).willReturn(groups);

        //when
        List result = manager.getAll();

        //then
        assertSame(groups, result);
    }

    @Test
    public void testSaveGroup() {
        log.debug("testing save...");

        //given
        final Group group = new Group();
        // enter all required fields

        given(dao.save(group)).willReturn(group);

        //when
        manager.save(group);

        //then
        verify(dao).save(group);
    }

    @Test
    public void testRemoveGroup() {
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
