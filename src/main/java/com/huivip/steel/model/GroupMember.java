package com.huivip.steel.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 6/6/15.
 */
@Entity
@Table(name="groupmember")
@Indexed
@XmlRootElement
public class GroupMember extends BaseObject implements Serializable{
    private Long id;
    private Group group;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="group_id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMember that = (GroupMember) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "id=" + id +
                ", group=" + group +
                ", user=" + user +
                '}';
    }
}
