package com.kyrutech.kyruforum.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "discussionid")
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "parentid")
    private Post parent;

    @Column
    private LocalDateTime timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (!user.equals(post.user)) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (!discussion.equals(post.discussion)) return false;
        if (parent != null ? !parent.equals(post.parent) : post.parent != null) return false;
        return timestamp.equals(post.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + discussion.hashCode();
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
