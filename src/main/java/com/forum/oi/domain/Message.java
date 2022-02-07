package com.forum.oi.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String time;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            mappedBy = "message")
    private Set<Article> article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {
    }

    public Message(String text, User user,  String time) {
        this.text = text;
        this.author = user;
        this.time = time;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Article> getArticle() {
        return article;
    }

    public void setArticle(Set<Article> article) {
        this.article = article;
    }
}
