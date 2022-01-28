package com.github.altaite.locator.engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EngineFromDirectory implements EngineFactory {

    @Override
    public Engine create(File file) {
        Engine engine = new Engine();
        long count = 0;
        try ( Stream<Path> walk = Files.walk(file.toPath())) {
            System.out.println("Walking " + file);
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            System.out.println("Found " + result.size() + " files.");
            for (String filename : result) {
                if (!filename.endsWith(".txt")) {
                    continue;
                }
                File f = new File(filename);
                if (f.length() > 100 * 1000) {
                    continue;
                }
                TextFile unit = createUnit(f);
                engine.add(unit);
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return engine;
    }

    private TextFile createUnit(File file) {
        WordFactory wf = new WordFactory();
        StringBuilder sb = new StringBuilder();
        TextFile unit = new TextFile(file);
        for (Word w : wf.create(file.getName())) {
            unit.add(w);
        }
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            unit.setContent(content);
            for (Word w : wf.create(content)) {
                unit.add(w);
            }
        } catch (IOException ex) {
            System.err.println(file.getAbsolutePath());
            throw new RuntimeException(file.getAbsolutePath(), ex);
        }
        return unit;
    }
}
