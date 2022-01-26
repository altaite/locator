package com.github.altaite.locator.execute;

import java.io.File;
import java.io.IOException;

public final class Executor {

    private final String vimPath = "d:\\t\\instal\\vim\\vim82\\gvim.exe";
    private final String q10Path = "d:\\t\\instal\\Q10\\q10.exe";
    private final String emacs = "d:\\t\\instal\\emacs\\bin\\runemacs.exe";
    private final String emacsClient = "d:\\t\\instal\\emacs\\bin\\emacsclientw.exe -t";
    private final String emacsDaemon = "d:\\t\\instal\\emacs\\bin\\emacs --daemon";
//emacs --daemon    
//emacsclient -t
//emacsclient -nw -c

    public void runEmacsDaemon() {
        run(emacsDaemon);
    }

    public void openInVim(File file) {
        open(vimPath, file);
    }

    public void openInQ10(File file) {
        open(q10Path, file);
    }

    public void openInEmacs(File file) {
        open(emacs, file);
    }

    public void openInEmacsClient(File file) {
        open(emacsClient, file);
    }

    private void open(String program, File file) {
        run(program + " \"" + file.getAbsolutePath() + "\"");
    }

    private void run(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("d:\\t\\instal\\vim\\vim82\\gvim.exe d:\\t\\file.txt");
    }*/
}
