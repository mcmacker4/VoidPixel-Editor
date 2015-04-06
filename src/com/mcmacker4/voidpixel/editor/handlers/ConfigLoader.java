package com.mcmacker4.voidpixel.editor.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcmacker4.voidpixel.editor.main.Game;

public class ConfigLoader {
	
	public static void load() {
		
		try {
			File f = new File("res/config.json");
			FileReader r = new FileReader(f);
			Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			Game.config = g.fromJson(r, DisplayConfig.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void save() {
		
		try {
			File f = new File("res/config.json");
			FileWriter r = new FileWriter(f);
			Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			String json = g.toJson(Game.config);
			r.write(json);
			r.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
