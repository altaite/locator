package com.github.altaite.locator.engine;

import java.util.ArrayList;
import java.util.List;

public class Units {

    private List<TextFile> units = new ArrayList<>();

    public Units() {

    }

    public Units(TextFile firstUnit) {
        units.add(firstUnit);
    }

    public void add(TextFile unit) {
        units.add(unit);
    }

    public List<TextFile> getList() {
        return units;
    }

    public boolean isEmpty() {
        return units.isEmpty();
    }

    public int size() {
        return units.size();
    }
}
