package com.huivip.steel.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunlaihui on 6/6/15.
 */
@Entity
@Table(name="news")
@Indexed
@XmlRootElement
public class News extends BaseObject implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Timestamp createTime= new Timestamp(new Date().getTime());
    private Timestamp updateTime = new Timestamp(new Date().getTime());
    private Timestamp expiredTime;
    private NewsType newsType;
    private User creater;
    private boolean ifAccessLimited;

    private Set<UserGroup> viewGroups=new HashSet<>();
    private Set<User>  viewUsers=new HashSet<>();

    private String thumbnailUrl;

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

    @Column(updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creater_id")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="newsType_id")
    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public boolean isIfAccessLimited() {
        return ifAccessLimited;
    }

    public void setIfAccessLimited(boolean ifAccessLimited) {
        this.ifAccessLimited = ifAccessLimited;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "newsViewGroups",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<UserGroup> getViewGroups() {
        return viewGroups;
    }

    public void setViewGroups(Set<UserGroup> viewGroups) {
        this.viewGroups = viewGroups;
    }
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "newsViewUsers",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getViewUsers() {
        return viewUsers;
    }

    public void setViewUsers(Set<User> viewUsers) {
        this.viewUsers = viewUsers;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (ifAccessLimited != news.ifAccessLimited) return false;
        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        if (createTime != null ? !createTime.equals(news.createTime) : news.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(news.updateTime) : news.updateTime != null) return false;
        if (expiredTime != null ? !expiredTime.equals(news.expiredTime) : news.expiredTime != null) return false;
        if (creater != null ? !creater.equals(news.creater) : news.creater != null) return false;
        if (viewGroups != null ? !viewGroups.equals(news.viewGroups) : news.viewGroups != null) return false;
        if (viewUsers != null ? !viewUsers.equals(news.viewUsers) : news.viewUsers != null) return false;
        return !(thumbnailUrl != null ? !thumbnailUrl.equals(news.thumbnailUrl) : news.thumbnailUrl != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (expiredTime != null ? expiredTime.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (ifAccessLimited ? 1 : 0);
        result = 31 * result + (viewGroups != null ? viewGroups.hashCode() : 0);
        result = 31 * result + (viewUsers != null ? viewUsers.hashCode() : 0);
        result = 31 * result + (thumbnailUrl != null ? thumbnailUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", expiredTime=" + expiredTime +
                ", creater=" + creater +
                ", ifAccessLimited=" + ifAccessLimited +
                ", viewGroups=" + viewGroups +
                ", viewUsers=" + viewUsers +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
