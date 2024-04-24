package com.app.users;

import com.app.notes.Note;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    private String password_hash;

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    private List<Note> notes;

    @NotNull
    private Boolean isAdmin;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password_hash=" + password_hash +
                '}';
    }

    public int getUid() {
        return id;
    }

    public void setUid(int uid) {
        this.id = uid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String  password_hash) {
        this.password_hash = password_hash;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void addNote(Note note){
        this.notes.add(note);
    }
}
