package com.app.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
    List<Note> findByTitle(String title);
}
