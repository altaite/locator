package com.github.altaite.locator.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TextFile {

    private final File file;
    private String content;
    private final List<Word> words;

    public TextFile(File file) {
        this.file = file;
        this.words = new ArrayList<>();
    }

    public void setContent(String s) {
        this.content = s;
    }

    public String getContent() {
        return content;
    }

    public void add(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return content;
    }
}
