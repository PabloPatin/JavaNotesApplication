package com.app.utils;


import com.app.notes.Note;
import com.app.tags.Tag;
import com.app.tags.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NoteMerger {

    private TagService tagService;

    private NoteMerger(TagService tagService) {
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public void merge(Note note, Note tempNote){
        String title = tempNote.getTitle();
        String text = tempNote.getText();
        Set<Tag> tags = tempNote.getTags();
        if (title!=null)
            note.setTitle(title);
        if (text!=null)
            note.setText(text);
        if (tags!=null){
            tagService.checkTags(tags);
            note.setTags(tags);
        }
    }
}
