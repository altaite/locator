package com.github.altaite.locator.engine;

import java.util.ArrayList;
import java.util.List;

public class Units {

    private List<Unit> units = new ArrayList<>();

    public Units() {

    }

    public Units(Unit firstUnit) {
        units.add(firstUnit);
    }

    public void add(Unit unit) {
        units.add(unit);
    }

    public List<Unit> getList() {
        return units;
    }

    public boolean isEmpty() {
        return units.isEmpty();
    }
}
