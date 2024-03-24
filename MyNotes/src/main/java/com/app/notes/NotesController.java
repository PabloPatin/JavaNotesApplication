package com.app.notes;

import com.app.tags.Tag;
import com.app.tags.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/notes")
@RestController
public class NotesController {
    private NotesService notesService;
    private TagService tagService;

    public NotesController(NotesService notesService, TagService tagService) {
        this.notesService = notesService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping(path = "{noteId}")
    public Note getNote(@PathVariable int noteId) {
        return notesService.getNoteById(noteId);
    }

    @GetMapping(params = {"find_by", "value"})
    public List<Note> getNotesBy(@RequestParam String find_by, @RequestParam String value){
        if (find_by.equals("title")) {
            return notesService.getNotesByTitle(value);
        }else if (find_by.equals("id")){
            return List.of(notesService.getNoteById(Integer.parseInt(value)));
        }
        else throw new IllegalArgumentException(value + "is not valid argument");
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody NoteTemplate tempNote) {
        Note note = new Note();
        note.setTitle(tempNote.title());
        note.setText(tempNote.text());
        Set<Tag> tags = tempNote.tags().stream()
                .map(tagName -> tagService.findOrCreateTag(tagName))
                .collect(Collectors.toSet());
        note.setTags(tags);
        return notesService.saveNote(note);
    }

    @PutMapping(path = "{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody NoteTemplate tempNote, @PathVariable int noteId) {
        Note existingNote = notesService.getNoteById(noteId);
        if (tempNote.title()!=null)
            existingNote.setTitle(tempNote.title());
        if (tempNote.text()!=null)
            existingNote.setText(tempNote.text());
        if (tempNote.tags()!=null){
        Set<Tag> tags = tempNote.tags().stream()
                .map(tagName -> tagService.findOrCreateTag(tagName))
                .collect(Collectors.toSet());
        existingNote.setTags(tags);
        }
        return notesService.saveNote(existingNote);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(@PathVariable int noteId){
        notesService.deleteNote(noteId);
    }

    @DeleteMapping
    public void deleteAllNotes(){
        notesService.deleteAllNotes();
    }
}
