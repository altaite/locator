package com.github.altaite.locator.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EngineFromDirectory implements EngineFactory {

    public EngineFromDirectory() {

    }

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
                Unit unit = createUnit(f);
                engine.add(unit);
                count++;
            }

            //result.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return engine;
    }

    private Unit createUnit(File file) {
        WordFactory wf = new WordFactory();
        StringBuilder sb = new StringBuilder();
        Unit unit = new Unit(file.getAbsolutePath());
        for (Word w : wf.create(file.getName())) {
            unit.add(w);
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                for (Word w : wf.create(line)) {
                    unit.add(w);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        unit.setContent(sb.toString());
        return unit;
    }
}
