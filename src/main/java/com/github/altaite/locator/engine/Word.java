package com.github.altaite.locator.engine;

public class Word {

	private String word;

	public Word(String s) {
		this.word = s.toLowerCase();
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Word other = (Word) o;
		return other.word.equals(word);
	}

	@Override
	public String toString() {
		return word;
	}

}
