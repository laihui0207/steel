package com.huivip.steel.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by sunlaihui on 6/12/15.
 */

@Entity
@Table(name = "documents")
@Indexed
@XmlRootElement
public class Documents extends BaseObject implements Serializable {
    Long id;
    String name;
    String introduction;
    String content;
    String comment;
    Timestamp createTime;
    User creater;
    Timestamp updateTime;
    User updater;
    String storePath;
    int docSize;

    DocType docType;
    boolean viewLimit;

    Set<User> viewUsers;
    Set<UserGroup> viewUserGroups;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name="creater_id",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @ManyToOne
    @JoinColumn(name="updater_id")
    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public int getDocSize() {
        return docSize;
    }

    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }
    @ManyToOne
    @JoinColumn(name="doctype_id")
    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public boolean isViewLimit() {
        return viewLimit;
    }

    public void setViewLimit(boolean viewLimit) {
        this.viewLimit = viewLimit;
    }
    @ManyToMany
    @JoinTable(
            name = "docViewUsers",
            joinColumns = {@JoinColumn(name = "doc_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getViewUsers() {
        return viewUsers;
    }

    public void setViewUsers(Set<User> viewUsers) {
        this.viewUsers = viewUsers;
    }
    @ManyToMany
    @JoinTable(
            name = "docViewGroups",
            joinColumns = {@JoinColumn(name = "doc_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<UserGroup> getViewUserGroups() {
        return viewUserGroups;
    }

    public void setViewUserGroups(Set<UserGroup> viewUserGroups) {
        this.viewUserGroups = viewUserGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Documents documents = (Documents) o;

        if (docSize != documents.docSize) return false;
        if (viewLimit != documents.viewLimit) return false;
        if (id != null ? !id.equals(documents.id) : documents.id != null) return false;
        if (name != null ? !name.equals(documents.name) : documents.name != null) return false;
        if (introduction != null ? !introduction.equals(documents.introduction) : documents.introduction != null)
            return false;
        if (content != null ? !content.equals(documents.content) : documents.content != null) return false;
        if (comment != null ? !comment.equals(documents.comment) : documents.comment != null) return false;
        if (createTime != null ? !createTime.equals(documents.createTime) : documents.createTime != null) return false;
        if (creater != null ? !creater.equals(documents.creater) : documents.creater != null) return false;
        if (updateTime != null ? !updateTime.equals(documents.updateTime) : documents.updateTime != null) return false;
        if (updater != null ? !updater.equals(documents.updater) : documents.updater != null) return false;
        if (storePath != null ? !storePath.equals(documents.storePath) : documents.storePath != null) return false;
        if (docType != null ? !docType.equals(documents.docType) : documents.docType != null) return false;
        if (viewUsers != null ? !viewUsers.equals(documents.viewUsers) : documents.viewUsers != null) return false;
        return !(viewUserGroups != null ? !viewUserGroups.equals(documents.viewUserGroups) : documents.viewUserGroups != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (storePath != null ? storePath.hashCode() : 0);
        result = 31 * result + docSize;
        result = 31 * result + (docType != null ? docType.hashCode() : 0);
        result = 31 * result + (viewLimit ? 1 : 0);
        result = 31 * result + (viewUsers != null ? viewUsers.hashCode() : 0);
        result = 31 * result + (viewUserGroups != null ? viewUserGroups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", creater=" + creater +
                ", updateTime=" + updateTime +
                ", updater=" + updater +
                ", storePath='" + storePath + '\'' +
                ", docSize=" + docSize +
                ", docType=" + docType +
                ", viewLimit=" + viewLimit +
                ", viewUsers=" + viewUsers +
                ", viewUserGroups=" + viewUserGroups +
                '}';
    }
}
