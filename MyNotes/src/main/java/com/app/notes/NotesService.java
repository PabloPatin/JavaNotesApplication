package com.app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotesService {
    private NotesRepository repository;
    @Autowired
    public NotesService(NotesRepository notesRepository){
        this.repository = notesRepository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public List<Note> getNotesByTitle(String title){
        return repository.findByTitle(title);
    }


    public Note getNoteById(int id){
        Optional<Note> note = repository.findById(id);
        return note.orElseThrow(() -> new IllegalArgumentException("Note not found with id " + id));
    }

    public ResponseEntity<?> saveNote(Note note) {
        repository.save(note);
        return ResponseEntity.ok(note);
    }

    public void deleteNote(int noteId){
        Note note = repository
                .findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id " + noteId));
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAll();
    }
}
