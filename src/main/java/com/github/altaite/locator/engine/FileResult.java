package com.github.altaite.locator.engine;

import java.io.File;

public class FileResult {

    private final TextFile textFile;
    private final Query query;
    private String snippet;

    public FileResult(TextFile textFile, Query query) {
        this.textFile = textFile;
        this.query = query;
        process();
    }

    private void process() {
        String s = textFile.getContent().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (Word w : query.getWords()) {
            int i = s.indexOf(w.toString());
            sb.append(" | ");
            sb.append(extractSnippet(i));

        }
        snippet = sb.toString();
    }

    private String extractSnippet(int i) {
        String s = textFile.getContent();
        int a = i - 20;
        int b = i + 20;
        if (a < 0) {
            a = 0;
        }
        if (b > s.length()) {
            b = s.length();
        }
        return s.substring(a, b);
    }

    public String getSnippet() {
        return snippet;
    }

    @Override
    public String toString() {
        return textFile.getFile().getName() + " " + snippet.replaceAll("\\p{Cntrl}", " ");
    }

    public File getFile() {
        return textFile.getFile();
    }
}
