package com.github.altaite.locator.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WordFactory {

	public WordFactory() {

	}

	public List<Word> create(String text) {
		List<Word> words = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(text, " \t\n:;,.'\"_-/*+<>?!@#$%^&()[]{}=~`");
		while (st.hasMoreTokens()) {
			String s = st.nextToken().trim();
			if (s.length() > 0) {
				words.add(new Word(s));
			}
		}
		return words;
	}

}
