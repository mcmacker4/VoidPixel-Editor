package com.mcmacker4.voidpixel.editor.main;

import com.mcmacker4.voidpixel.editor.handlers.ConfigLoader;
import com.mcmacker4.voidpixel.editor.handlers.DisplayConfig;
import com.mcmacker4.voidpixel.editor.handlers.DisplayHandler;
import com.mcmacker4.voidpixel.editor.handlers.GLHandler;

public class Game {
	
	public static DisplayConfig config;
	public static String TITLE = "VoidPixel MapEditor by McMacker4";
	
	private GameLoop loop;
	
	public Game() {
		
		ConfigLoader.load();
		if(config.WIDTH < 600) config.WIDTH = 600;
		if(config.HEIGHT < 600) config.HEIGHT = 600;
		
		DisplayHandler.init(config.WIDTH, config.HEIGHT, TITLE);
		GLHandler.init(config.fov, (float) config.WIDTH / (float) config.HEIGHT, 0.1f, 100);
		
		loop = new GameLoop();
		loop.gameLoop();
		
		DisplayHandler.destroy();
		
	}
	
	public static int getWidth() {
		return config.WIDTH;
	}
	public static int getHeight() {
		return config.HEIGHT;
	}
	public static boolean isFullscreen() {
		return config.fullscreen;
	}
	public static boolean isVSync() {
		return config.vsync;
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < args.length; i++) {
			String a = args[i];
			if(a.startsWith("#createChunks") && a.length() == 14) {
				GameLoop.createChunks = true;
				char c = a.charAt(a.length() - 1);
				String n = String.valueOf(c);
				System.out.println(Integer.parseInt(n));
				GameLoop.chunkSize = Integer.parseInt(n);
			}
		}
		
		new Game();
	}
	
}
