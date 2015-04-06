package com.mcmacker4.voidpixel.editor.handlers;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.mcmacker4.voidpixel.editor.gui.GUI;
import com.mcmacker4.voidpixel.editor.gui.Inventory;
import com.mcmacker4.voidpixel.editor.main.GameLoop;
import com.mcmacker4.voidpixel.editor.view.Camera;
import com.mcmacker4.voidpixel.editor.world.BlockHandler;
import com.mcmacker4.voidpixel.editor.world.Onion;

public class InputHandler {
	
	public static void get() {
		
		if(!GameLoop.isPaused()) {
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W)) Camera.goForward();
			if(Keyboard.isKeyDown(Keyboard.KEY_S)) Camera.goBackward();
			if(Keyboard.isKeyDown(Keyboard.KEY_A)) Camera.goLeft();
			if(Keyboard.isKeyDown(Keyboard.KEY_D)) Camera.goRight();
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) Camera.goUp();
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) Camera.goDown();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_Z)) BlockHandler.destroyBlock();
			if(Keyboard.isKeyDown(Keyboard.KEY_X)) BlockHandler.putBlock();
			
		}
		
		while(Mouse.next()) {
			if(Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					BlockHandler.destroyBlock();
					GUI.mousePressed();
				}
				if(Mouse.getEventButton() == 1) {
					BlockHandler.putBlock();
				}
				if(Mouse.getEventButton() == 2) {
					Inventory.setSelected();
				}
			}
			if(!Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					GUI.mouseReleased();
				}
			}
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				GUI.keyPressed();
				if(Keyboard.getEventKey() == Keyboard.KEY_E) {
					GUI.toggleInventory();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					GUI.togglePause();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_F3) {
					GUI.toggleDebug();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_O) {
					ConfigLoader.save();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_P) {
					ConfigLoader.load();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_L) {
					Onion.loadChunks();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_K) {
					Onion.saveChunks();
				}
			}
		}
		
	}
	
}
