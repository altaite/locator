package com.github.altaite.locator.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Engine {

    private Map<Word, Units> words = new HashMap<>();

    protected void add(Unit unit) {
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

    public List<Unit> findOrMatches(Query q) {
        List<Unit> list = new ArrayList<>();
        for (Word word : q.getWords()) {
            Units units = words.get(word);
            if (units != null) {
                list.addAll(units.getList());
            }
        }
        return list;
    }

    public List<Unit> findAndMatches(Query q) {
        Set<Unit> intersection = new HashSet<>();
        boolean initialized = false;
        for (Word word : q.getWords()) {
            Units u = find(word);
            if (initialized) {
                intersection.retainAll(u.getList());
            } else if (!u.getList().isEmpty()) {
                intersection = new HashSet<>(u.getList());
            }
            if (!u.isEmpty()) {
                initialized = true;
            }
        }
        return new ArrayList<>(intersection);
    }

    // why is word mapped to Units (multiple files), see if it still makes sense
    // EngineFromDirectory has Unit as a single file
    public List<Unit> align(String queryString) {
        for (Units u : words.values()) {
            //String text = u.getText();
        }
        return null;
    }

    public void print() {
        System.out.println("------- PRINTING ENGINE -------");
        System.out.println("Words: " + words.size());
        for (Word w : words.keySet()) {
            System.out.println(w.toString());
            Units units = words.get(w);
            for (Unit unit : units.getList()) {
                for (Word word : unit.getWords()) {
                    System.out.print(word + "//");
                }
                System.out.println();
                System.out.println(unit.toString());
                System.out.println("");
                //System.out.println("  " + unit.toString());
            }
        }
        System.out.println("--------------------------------");
    }
}
