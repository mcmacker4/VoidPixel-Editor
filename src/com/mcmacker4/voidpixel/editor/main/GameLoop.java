package com.mcmacker4.voidpixel.editor.main;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mcmacker4.voidpixel.editor.gui.GUI;
import com.mcmacker4.voidpixel.editor.handlers.InputHandler;
import com.mcmacker4.voidpixel.editor.material.Textures;
import com.mcmacker4.voidpixel.editor.util.PrintInstructions;
import com.mcmacker4.voidpixel.editor.util.Timer;
import com.mcmacker4.voidpixel.editor.view.Camera;
import com.mcmacker4.voidpixel.editor.world.Onion;

public class GameLoop {
	
	private static GUI gui;
	private static boolean stopped;
	
	public static Onion onion;
	
	public static boolean createChunks;
	public static int chunkSize;
	
	public GameLoop() {}
	
	public void gameLoop() {
		
		Timer.init();
		init();
		
		while(!Display.isCloseRequested()) {
			
			if(stopped) return;
			
			InputHandler.get();
			Timer.calculateFPS();
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			Timer.getDelta();
			
			update();
			render();
				
			Display.update();
//			Display.sync(1999);
		}
		
	}
	
	public void init() {
		Mouse.setCursorPosition(Game.getWidth() / 2, Game.getHeight() / 2);
		Mouse.setGrabbed(true);
		Textures.loadAll();
		
		PrintInstructions.print();

		ByteBuffer[] ba = new ByteBuffer[2];
		ba[0] = ByteBuffer.wrap(Textures.LOGO16.getTextureData());
		ba[1] = ByteBuffer.wrap(Textures.LOGO32.getTextureData());
		Display.setIcon(ba);
		
		onion = new Onion(createChunks, chunkSize);
		
		gui = new GUI();
	}
	
	public void update() {
		Camera.useView();
	}
	
	public void render() {
		onion.genCallList();
		onion.draw();
		gui.draw();
	}
	
	public static boolean isPaused() {
		return gui.isPaused();
	}
	
	public static void stop() {
		stopped = true;
	}
	
}
