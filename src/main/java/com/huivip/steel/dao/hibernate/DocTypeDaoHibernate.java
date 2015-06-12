package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.DocType;
import com.huivip.steel.dao.DocTypeDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("docTypeDao")
public class DocTypeDaoHibernate extends GenericDaoHibernate<DocType, Long> implements DocTypeDao {

    public DocTypeDaoHibernate() {
        super(DocType.class);
    }
}
