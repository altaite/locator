package com.github.altaite.locator;

import java.io.File;

public final class HitLink {

    private final File file;
    private final String label;

    public HitLink(File file) {
        this.file = file;
        this.label = getLabel(file);
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return label;
    }

    private String getLabel(File f) {
        String l = f.getName();
        if (l.length() > 100) {
            l = "..." + l.substring(l.length() - 100);
        }
        return l;
    }

}
