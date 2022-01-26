package com.github.altaite.locator;

import com.github.altaite.locator.engine.Engine;
import com.github.altaite.locator.engine.EngineFactory;
import com.github.altaite.locator.engine.EngineFromDirectory;
import com.github.altaite.locator.engine.EngineFromTxt;
import com.github.altaite.locator.engine.Query;
import com.github.altaite.locator.engine.Unit;
import java.io.File;
import java.util.List;

public class Test {

	
	/*
	TODO next
	directory reader
	window checks if input is new on every key or so
	prints hits instantly
	*/
	
	public static void main(String[] args) {
		/*EngineFactory ef = new EngineFromTxt();
		Engine e = ef.create(new File("d:/t/java/locator/data/txt.txt"));
		//e.print();
		//Query q = new Query("idea also");
		Query q = new Query("high");

		List<Unit> units = e.findOrMatches(q);
		System.out.println("Found: " + units.size());
		for (Unit u : units) {
			System.out.println(u.toString());
		}*/
		
		
		EngineFactory ef = new EngineFromDirectory();
		Engine e = ef.create(new File("d:/t/java/locator/data/gmail2021"));
			Query q = new Query("rose");
		System.out.println("Created.");
		List<Unit> hits=  e.findAndMatches(q);
		System.out.println("Hits: " + hits.size());
	}
}
