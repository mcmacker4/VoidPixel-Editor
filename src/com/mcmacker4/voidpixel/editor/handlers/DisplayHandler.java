package com.mcmacker4.voidpixel.editor.handlers;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.mcmacker4.voidpixel.editor.main.Game;

public class DisplayHandler {
	
	private static boolean destroyed;

	public static void init(int width, int height, String title) {
		
		try {
			if(Game.isFullscreen()) {
				Game.config.WIDTH = Display.getDesktopDisplayMode().getWidth();
				Game.config.HEIGHT = Display.getDesktopDisplayMode().getHeight();
				Display.setDisplayMode(Display.getDesktopDisplayMode());
			} else {
				Display.setDisplayMode(new DisplayMode(width, height));
			}
			Display.setFullscreen(Game.isFullscreen());
			Display.setVSyncEnabled(Game.isVSync());
			Display.create();
			Mouse.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void destroy() {
		destroyed = true;
		Display.destroy();
		Mouse.destroy();
		Keyboard.destroy();
	}
	
	public static boolean isDestroyed() {
		return destroyed;
	}
	
	public static boolean isActive() {
		return Display.isActive();
	}
	
}
