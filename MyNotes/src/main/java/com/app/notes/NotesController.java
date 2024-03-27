package com.app.notes;

import com.app.exceptions.BadRequestException;
import com.app.utils.NoteMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/notes")
@RestController
public class NotesController {
    private NotesService service;
    private NoteMerger noteMerger;

    @Autowired
    public NotesController(NotesService notesService, NoteMerger noteMerger) {
        this.service = notesService;
        this.noteMerger = noteMerger;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return service.getAllNotes();
    }

    @GetMapping(path = "{noteId}")
    public Note getNote(@PathVariable int noteId) {
        return service.getNoteById(noteId);
    }

    @GetMapping(params = {"find_by", "value"})
    public List<Note> getNotesBy(@RequestParam String find_by, @RequestParam String value){
        if (find_by.equals("title")) {
            return service.getNotesByTitle(value);
        }else if (find_by.equals("id")){
            return List.of(service.getNoteById(Integer.parseInt(value)));
        }
        else throw new BadRequestException("find_by=" + find_by + " is not valid argument");
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody Note tempNote) {
        Note note = new Note();
        noteMerger.merge(note, tempNote);
        return service.saveNote(note);
    }

    @PutMapping(path = "{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody Note tempNote, @PathVariable int noteId) {
        Note existingNote = service.getNoteById(noteId);
        noteMerger.merge(existingNote, tempNote);
        return service.saveNote(existingNote);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(@PathVariable int noteId){
        service.deleteNote(noteId);
    }

    @DeleteMapping
    public void deleteAllNotes(){
        service.deleteAllNotes();
    }
}
