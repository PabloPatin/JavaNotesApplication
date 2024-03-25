package com.app.tags;

import com.app.notes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.tokens.TagToken;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}
