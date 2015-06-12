package com.huivip.steel.model;

import com.sun.istack.NotNull;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by sunlaihui on 6/6/15.
 */
@Entity
@Table(name="post")
@Indexed
@XmlRootElement
public class Post extends BaseObject implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Timestamp createTime=new Timestamp(new Date().getTime());
    private Timestamp updateTime=new Timestamp(new Date().getTime());
    private Timestamp lastReplyTime;
    private User lastReplyUser;
    private User creater;
    private String thumbnailUrl;
    private PostType postType;

    // if true, all user can reply the post, if false, just allow reply groups and reply user can reply the post
    private boolean ifAccessAllReply= true;
    private Set<UserGroup> replyGroups=new HashSet<>();
    private Set<User>  replyUsers=new HashSet<>();
    private List<Reply> replies=new ArrayList<>();

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
    @NotNull
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull
    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(updatable = false )
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
    @ManyToOne
    @JoinColumn(name="creater_id")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isIfAccessAllReply() {
        return ifAccessAllReply;
    }

    public void setIfAccessAllReply(boolean ifAccessAllReply) {
        this.ifAccessAllReply = ifAccessAllReply;
    }
    @ManyToMany
    @JoinTable(
            name = "postReplyGroups",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<UserGroup> getReplyGroups() {
        return replyGroups;
    }

    public void setReplyGroups(Set<UserGroup> replyGroups) {
        this.replyGroups = replyGroups;
    }
    @ManyToMany
    @JoinTable(
            name = "postReplyUsers",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getReplyUsers() {
        return replyUsers;
    }

    public void setReplyUsers(Set<User> replyUsers) {
        this.replyUsers = replyUsers;
    }

    public Timestamp getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Timestamp lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }
    @ManyToOne
    @JoinColumn(name = "lastReplier_id")
    public User getLastReplyUser() {
        return lastReplyUser;
    }

    public void setLastReplyUser(User lastReplyUser) {
        this.lastReplyUser = lastReplyUser;
    }
    @ManyToOne
    @JoinColumn(name="posttype_id")
    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }
    @OneToMany(mappedBy = "post")
    @OrderBy("replyTime")
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (ifAccessAllReply != post.ifAccessAllReply) return false;
        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (createTime != null ? !createTime.equals(post.createTime) : post.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(post.updateTime) : post.updateTime != null) return false;
        if (creater != null ? !creater.equals(post.creater) : post.creater != null) return false;
        if (thumbnailUrl != null ? !thumbnailUrl.equals(post.thumbnailUrl) : post.thumbnailUrl != null) return false;
        if (replyGroups != null ? !replyGroups.equals(post.replyGroups) : post.replyGroups != null) return false;
        return !(replyUsers != null ? !replyUsers.equals(post.replyUsers) : post.replyUsers != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (thumbnailUrl != null ? thumbnailUrl.hashCode() : 0);
        result = 31 * result + (ifAccessAllReply ? 1 : 0);
        result = 31 * result + (replyGroups != null ? replyGroups.hashCode() : 0);
        result = 31 * result + (replyUsers != null ? replyUsers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creater=" + creater +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", ifAccessAllReply=" + ifAccessAllReply +
                ", replyGroups=" + replyGroups +
                ", replyUsers=" + replyUsers +
                '}';
    }
}
