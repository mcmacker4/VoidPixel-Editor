package com.mcmacker4.voidpixel.editor.util;

import org.lwjgl.Sys;

public class Timer {
	
	private static long last;
	private static long lastFPS;
	private static int currentDelta;
	private static int fps;
	private static int FPS;
	
	public static void init() {
		last = getTime();
		lastFPS = getTime();
		getDelta();
	}

	public static long getTime() {
		return(Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static int getDelta() {
		long now = getTime();
		int delta = (int) (now - last);
		last = now;
		if(delta == 0) delta = 1;
		currentDelta = delta;
		
		return delta;
	}
	
	public static int getCurrentDelta() {
		return currentDelta;
	}
	
	public static void calculateFPS() {
		long now = getTime();
		if(now - lastFPS > 1000) {
			lastFPS = now;
			FPS = fps;
			fps = 0;
		}
		fps++;
	}
	
	public static int getFPS() {
		return FPS;
	}
	
}
