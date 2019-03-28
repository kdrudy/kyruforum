package com.kyrutech.kyruforum.entities;

import javax.persistence.*;

@Entity
public class Discussion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sectionid")
    private Section section;

    @Transient
    private int postCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discussion that = (Discussion) o;

        if (id != that.id) return false;
        if (postCount != that.postCount) return false;
        if (!title.equals(that.title)) return false;
        if (!user.equals(that.user)) return false;
        return section.equals(that.section);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + section.hashCode();
        result = 31 * result + postCount;
        return result;
    }
}
