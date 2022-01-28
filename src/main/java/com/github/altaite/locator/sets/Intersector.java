package com.github.altaite.locator.sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Intersector<T> {

    private Set<T> set = null;

    public void add(Collection<T> collection) {
        if (set == null) {
            set = new HashSet<>();
            set.addAll(collection);
        } else {
            set.retainAll(collection);
        }
    }

    public List<T> getIntersection() {
        if (set == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(set);
    }

    /*   public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(4);
        a.add(3);
        a.add(2);
        b.add(1);
        b.add(2);
        Intersector<Integer> x = new Intersector<>();
        x.add(a);
        x.add(b);
        System.out.println(x.getIntersection().size());
        Intersector<Integer> y = new Intersector<>();
        y.add(b);
        y.add(a);
        System.out.println(y.getIntersection().size());
    }*/
}
