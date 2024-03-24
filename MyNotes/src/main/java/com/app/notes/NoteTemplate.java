package com.app.notes;

import java.util.List;

public record NoteTemplate (String title, String text, List<String> tags){}
