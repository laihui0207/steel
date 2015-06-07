package com.huivip.steel.dao;

import com.huivip.steel.model.UserGroup;
import org.springframework.stereotype.Repository;

/**
 * An interface that provides a data management interface to the UserGroup table.
 */
@Repository("userGroupDao")
public interface UserGroupDao extends GenericDao<UserGroup, Long> {

}