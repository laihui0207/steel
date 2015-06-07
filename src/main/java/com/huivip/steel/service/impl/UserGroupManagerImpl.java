package com.huivip.steel.service.impl;

import com.huivip.steel.dao.UserGroupDao;
import com.huivip.steel.model.UserGroup;
import com.huivip.steel.service.UserGroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@Service("userGroupManager")
@WebService(serviceName = "UserGroupService", endpointInterface = "com.huivip.steel.service.UserGroupManager")
public class UserGroupManagerImpl extends GenericManagerImpl<UserGroup, Long> implements UserGroupManager{
    private UserGroupDao userGroupDao;

    @Autowired
    public UserGroupManagerImpl(UserGroupDao userGroupDao) {
        super(userGroupDao);
        this.userGroupDao = userGroupDao;
    }

    @Autowired
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        return userGroupDao.getAll();
    }

    @Override
    public UserGroup getUserGroup(String id) {
        return userGroupDao.get(Long.parseLong(id));
    }

    @Override
    public UserGroup saveUserGroup(UserGroup userGroup) {
        return null;
    }

    @Override
    public String removeUserGroup(String id) {
        userGroupDao.remove(Long.parseLong(id));
        return "OK";
    }
}