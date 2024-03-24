package com.app.notes;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/notes")
@RestController
public class NotesController {
    private NotesRepository repository;

    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }

    @GetMapping(params = {})
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @GetMapping(params = {"find_by", "value"})
    private Note getNoteByTitle(@RequestParam String find_by, @RequestParam String value){
        if (find_by.equals("title")) {
            return repository.findByTitle(value).get(0);
        }else if (find_by.equals("id")){
            int noteId = Integer.parseInt(value);
            Optional<Note> note = repository.findById(noteId);
            return note.orElse(null);
        }
        else return null;
    }

    @GetMapping(params = {"find_all_by", "value"})
    private List<Note> getNotesByTitle(@RequestParam String find_by, @RequestParam String value){
        if (find_by.equals("title")) {
            return repository.findByTitle(value);
        }
        else return null;
    }

    @PostMapping
    private void addNote(@RequestBody Note note) {
        repository.save(note);
    }

    @PutMapping(path = "{noteId}")
    private void updateNote(@RequestBody Note updatedNote, @PathVariable int noteId) {
        Note existingNote = repository
                .findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id " + noteId));
        existingNote.setText(updatedNote.getText());
        existingNote.setTitle(updatedNote.getTitle());
        repository.save(existingNote);
    }

    @DeleteMapping(path = "{noteId}")
    private void deleteNote(@PathVariable int noteId){
        Note note = repository
                .findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id " + noteId));
        repository.delete(note);
    }

    @DeleteMapping
    private void deleteAllNotes(){
        repository.deleteAll();
    }
}
