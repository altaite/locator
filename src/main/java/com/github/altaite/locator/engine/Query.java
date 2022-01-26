package com.github.altaite.locator.engine;

import java.util.List;

public class Query {

	private List<Word> words;
	private WordFactory wf = new WordFactory();

	public Query(String text) {
		words = wf.create(text);
	}

	public List<Word> getWords() {
		return words;
	}

}
