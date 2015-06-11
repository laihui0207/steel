package com.huivip.steel.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by sunlaihui on 6/11/15.
 */
@Entity
@Table(name="messages")
@Indexed
@XmlRootElement
public class Message extends BaseObject implements Serializable {
    Long id;
    String title;
    String content;
    User creater;
    Timestamp createTime;
    User Sender;
    Timestamp sendTime;
    Set<User> recerivers;
    Set<UserGroup> receriveGroups;
    boolean status;
    User updater;
    Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(name = "creater_id",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "messagereceriveusers",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getRecerivers() {
        return recerivers;
    }

    public void setRecerivers(Set<User> recerivers) {
        this.recerivers = recerivers;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "messagerecerivegroups",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = @JoinColumn(name = "usergroup_id")
    )
    public Set<UserGroup> getReceriveGroups() {
        return receriveGroups;
    }

    public void setReceriveGroups(Set<UserGroup> receriveGroups) {
        this.receriveGroups = receriveGroups;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @ManyToOne
    @JoinColumn(name = "updater_id")
    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (status != message.status) return false;
        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (title != null ? !title.equals(message.title) : message.title != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (creater != null ? !creater.equals(message.creater) : message.creater != null) return false;
        if (createTime != null ? !createTime.equals(message.createTime) : message.createTime != null) return false;
        if (Sender != null ? !Sender.equals(message.Sender) : message.Sender != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        if (recerivers != null ? !recerivers.equals(message.recerivers) : message.recerivers != null) return false;
        if (receriveGroups != null ? !receriveGroups.equals(message.receriveGroups) : message.receriveGroups != null)
            return false;
        if (updater != null ? !updater.equals(message.updater) : message.updater != null) return false;
        return !(updateTime != null ? !updateTime.equals(message.updateTime) : message.updateTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (Sender != null ? Sender.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (recerivers != null ? recerivers.hashCode() : 0);
        result = 31 * result + (receriveGroups != null ? receriveGroups.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creater=" + creater +
                ", createTime=" + createTime +
                ", Sender=" + Sender +
                ", sendTime=" + sendTime +
                ", recerivers=" + recerivers +
                ", receriveGroups=" + receriveGroups +
                ", status=" + status +
                ", updater=" + updater +
                ", updateTime=" + updateTime +
                '}';
    }
}
