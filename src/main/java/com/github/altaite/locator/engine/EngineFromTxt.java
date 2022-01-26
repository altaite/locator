package com.github.altaite.locator.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
// Unit is now whole file, in EngineFromDirectory

@Deprecated
public class EngineFromTxt implements EngineFactory {

    public EngineFromTxt() {

    }

    @Override
    public Engine create(File file) {
        Engine engine = new Engine();
        WordFactory wf = new WordFactory();
        long lineNumber = 1;
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Unit unit = new Unit("" + (lineNumber++), line);
                for (Word w : wf.create(line)) {
                    unit.add(w);
                }
                engine.add(unit);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return engine;
    }
}
