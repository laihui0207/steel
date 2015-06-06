package com.huivip.steel.service.impl;

import com.huivip.steel.dao.GroupDao;
import com.huivip.steel.model.Group;
import com.huivip.steel.service.GroupManager;
import com.huivip.steel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("groupManager")
@WebService(serviceName = "GroupService", endpointInterface = "com.huivip.steel.service.GroupManager")
public class GroupManagerImpl extends GenericManagerImpl<Group, Long> implements GroupManager {
    GroupDao groupDao;

    @Autowired
    public GroupManagerImpl(GroupDao groupDao) {
        super(groupDao);
        this.groupDao = groupDao;
    }
}