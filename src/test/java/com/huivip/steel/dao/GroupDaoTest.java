package com.huivip.steel.dao;

import com.huivip.steel.model.Group;
import com.huivip.steel.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupDaoTest extends BaseDaoTestCase {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserDao userDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveGroup() {
        Group group = new Group();
        User user = userDao.get(-1L);
        // enter all required fields
        group.getMembers().add(user);
        log.debug("adding group...");
        group = groupDao.save(group);

        group = groupDao.get(group.getId());
        assertEquals(1,group.getMembers().size());
        assertNotNull(group.getMembers());
        assertNotNull(group.getId());

        log.debug("removing group...");

        groupDao.remove(group.getId());

        // should throw DataAccessException 
        groupDao.get(group.getId());
    }
}