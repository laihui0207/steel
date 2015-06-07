package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.UserGroup;
import com.huivip.steel.dao.UserGroupDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("userGroupDao")
public class UserGroupDaoHibernate extends GenericDaoHibernate<UserGroup, Long> implements UserGroupDao {

    public UserGroupDaoHibernate() {
        super(UserGroup.class);
    }
}
