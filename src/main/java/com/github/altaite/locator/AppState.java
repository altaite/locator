package com.github.altaite.locator;

import java.util.List;

public class AppState {

    private long hitNumber;
    private long hitPointer;
    private List<String> hitList;

    public long getHitPointer() {
        return hitPointer;
    }

    public void setHits(long number) {
        this.hitNumber = number;
    }

    public void hitPointerDown() {
        if (hitPointer < hitNumber) {
            hitPointer++;
        }
    }

    public void hitPointerUp() {
        if (hitPointer > 0) {
            hitPointer--;
        }
    }

    public void setHitList(List<String> hitList) {
        this.hitList = hitList;
    }
}
