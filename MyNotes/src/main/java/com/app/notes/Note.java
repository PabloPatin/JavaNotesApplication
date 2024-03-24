package com.app.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.grammars.hql.HqlParser;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp")
    private String creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private String lastUpdateTime;

    public String getCreationDateTime() {
        return creationTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationTime = creationDateTime;
    }

    public String getLastUpdateDateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateDateTime(String lastUpdateDateTime) {
        this.lastUpdateTime = lastUpdateDateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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

}
