package com.huivip.steel.dao;

import com.huivip.steel.dao.BaseDaoTestCase;
import com.huivip.steel.model.Group;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupDaoTest extends BaseDaoTestCase {
    @Autowired
    private GroupDao groupDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveGroup() {
        Group group = new Group();

        // enter all required fields

        log.debug("adding group...");
        group = groupDao.save(group);

        group = groupDao.get(group.getId());

        assertNotNull(group.getId());

        log.debug("removing group...");

        groupDao.remove(group.getId());

        // should throw DataAccessException 
        groupDao.get(group.getId());
    }
}