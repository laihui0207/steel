package com.huivip.steel.dao.hibernate;

import com.huivip.steel.model.Documents;
import com.huivip.steel.dao.DocumentsDao;
import com.huivip.steel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("documentsDao")
public class DocumentsDaoHibernate extends GenericDaoHibernate<Documents, Long> implements DocumentsDao {

    public DocumentsDaoHibernate() {
        super(Documents.class);
    }
}
