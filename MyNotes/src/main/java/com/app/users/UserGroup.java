package com.app.users;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(indexes = @Index(name = "group_id", columnList = "name"))
public class UserGroup {
    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
