package com.forum.oi.domain;

import javax.persistence.*;

@Entity
@Table(name = "text_for_bot")
public class TextForBot {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String text;

    private boolean used;

    public TextForBot() {
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
