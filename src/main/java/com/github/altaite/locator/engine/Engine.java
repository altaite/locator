package com.github.altaite.locator.engine;

import com.github.altaite.locator.sets.Intersector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Engine {

    private Map<Word, Units> words = new HashMap<>();

    protected void add(TextFile unit) {
        for (Word word : unit.getWords()) {
            Units units = words.get(word);
            if (units == null) {
                units = new Units(unit);
                words.put(word, units);
            } else {
                units.add(unit);
            }
        }
    }

    private Units find(Word word) {
        Units u = words.get(word);
        if (u == null) {
            u = new Units();
        }
        return u;
    }

    private List<TextFile> findOrMatches(Query q) {
        List<TextFile> list = new ArrayList<>();
        for (Word word : q.getWords()) {
            Units units = words.get(word);
            if (units != null) {
                list.addAll(units.getList());
            }
        }
        return list;
    }

    private List<TextFile> findAndMatches(Query q) {
        Intersector<TextFile> intersector = new Intersector<>();
        for (Word word : q.getWords()) {
            Units us = find(word);
            intersector.add(us.getList());
        }
        return intersector.getIntersection();
    }

    public List<FileResult> search(Query q) {
        List<TextFile> files = findAndMatches(q);
        List<FileResult> results = new ArrayList<>();
        files.forEach(textFile -> results.add(new FileResult(textFile, q)));
        return results;
    }
}
