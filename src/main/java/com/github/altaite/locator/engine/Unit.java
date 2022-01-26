package com.github.altaite.locator.engine;

import java.util.ArrayList;
import java.util.List;

public final class Unit {

    private String id;
    private String content;
    private List<Word> words;

    @Deprecated
    public Unit(String id, String content) {
        this.id = id;
        this.content = content;
        this.words = new ArrayList<>();
    }

    public Unit(String id) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String toString() {
        return content;
    }
}
