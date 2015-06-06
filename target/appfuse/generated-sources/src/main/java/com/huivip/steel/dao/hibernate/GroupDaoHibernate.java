package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.Group;
import com.huivip.steel.dao.GroupDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDaoHibernate extends GenericDaoHibernate<Group, Long> implements GroupDao {

    public GroupDaoHibernate() {
        super(Group.class);
    }
}
