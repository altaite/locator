package com.github.altaite.locator.engine;

import java.io.File;

public interface EngineFactory {

	public Engine create(File file);
}
