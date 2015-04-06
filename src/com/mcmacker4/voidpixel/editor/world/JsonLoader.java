package com.mcmacker4.voidpixel.editor.world;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonLoader {
	
	private final static String loadDir = "res/chunks/";
	private final static String saveDir = "res/chunks/";

	public static void loadChunks() {
		
		ChunkStorage.storage.clear();
		try {
			File dir = new File(loadDir);
			for(File fil : dir.listFiles()) {
				if(fil.getName().startsWith("chunk_")) {
					FileReader r = new FileReader(fil);
					Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
					
					Chunk c = g.fromJson(r, Chunk.class);
					c.setFileName(fil.getName());
					ChunkStorage.storage.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveChunks() {
		
		try {
			for(Chunk c : ChunkStorage.storage) {
				File dir = new File(saveDir + c.getFileName());
				FileWriter r = new FileWriter(dir);
				Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String json = g.toJson(c);
				r.write(json);
				r.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
