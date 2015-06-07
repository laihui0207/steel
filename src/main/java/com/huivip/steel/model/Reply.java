package com.huivip.steel.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sunlaihui on 6/6/15.
 */
@Entity
@Table(name="replies")
@Indexed
@XmlRootElement
public class Reply extends BaseObject implements Serializable {

    private Long id;
    private String content;
    private Post post;
    private User replier;
    private Timestamp replyTime=new Timestamp(new Date().getTime());
    private Timestamp updateTime=new Timestamp(new Date().getTime());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(name="post_id")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    @ManyToOne
    @JoinColumn(name="replier_id")
    public User getReplier() {
        return replier;
    }

    public void setReplier(User replier) {
        this.replier = replier;
    }
    @Column(updatable = false)
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
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

        Reply reply = (Reply) o;

        if (id != null ? !id.equals(reply.id) : reply.id != null) return false;
        if (content != null ? !content.equals(reply.content) : reply.content != null) return false;
        if (post != null ? !post.equals(reply.post) : reply.post != null) return false;
        if (replier != null ? !replier.equals(reply.replier) : reply.replier != null) return false;
        return !(replyTime != null ? !replyTime.equals(reply.replyTime) : reply.replyTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (replier != null ? replier.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post=" + post +
                ", replier=" + replier +
                ", replyTime=" + replyTime +
                '}';
    }
}
