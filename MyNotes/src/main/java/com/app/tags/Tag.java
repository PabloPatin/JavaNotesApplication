package com.app.tags;

import jakarta.persistence.*;

@Entity
@Table(indexes = @Index(name = "id", columnList = "name"))
public class Tag {
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
