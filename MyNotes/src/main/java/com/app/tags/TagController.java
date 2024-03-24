package com.app.tags;

import com.app.notes.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/tags")
@RestController
public class TagController {
    private TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return service.getAllTags();
    }
}
