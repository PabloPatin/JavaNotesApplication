package com.app.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService {
    private TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAllTags(){
        return repository.findAll();
    }

    public void createTag(Tag tag){
        repository.save(tag);
    }

    public Tag findOrCreateTag(String name){
        Tag tag = repository.findByName(name);
        if (tag==null){
            tag = new Tag(name);
            repository.save(tag);
        }
        return tag;
    }
}
