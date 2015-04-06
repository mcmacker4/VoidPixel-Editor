package com.mcmacker4.voidpixel.editor.gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import com.mcmacker4.voidpixel.editor.main.Game;
import com.mcmacker4.voidpixel.editor.main.GameLoop;
import com.mcmacker4.voidpixel.editor.util.Button;
import com.mcmacker4.voidpixel.editor.util.FontLoader;
import com.mcmacker4.voidpixel.editor.world.Onion;

public class PauseMenu {
	
	private final int CONTINUE = 0;
	private final int SAVE_CHUNKS = 1;
	private final int EXIT = 2;
	
	private boolean show;
	private boolean exitConfirmation;
	
	private final String[] choices = new String[] {
			"Continue",
			"Save Chunks",
			"Exit"
	};
	private final String[] exitChoices = new String[] {
			"YES",
			"NO"
	};
	
	private Button[] buttons = new Button[choices.length];
	private Button[] exitButtons = new Button[exitChoices.length];
	
	private TrueTypeFont font = FontLoader.MAIN_FONT;
	
	public PauseMenu() {
		for(int i = 0; i < choices.length; i++) {
			buttons[i] = new Button(choices[i], 0, 290 + i * 30, i);
			buttons[i].centerHorizontal();
		}
		for(int i = 0; i < exitChoices.length; i++) {
			exitButtons[i] = new Button(exitChoices[i], 0, 300 + i * 30, i);
			exitButtons[i].centerHorizontal();
		}
	}
	
	public void draw() {
		if(show) {
			if(exitConfirmation) {
				font.drawString(Game.getWidth() / 2 - font.getWidth("ALL WORK NOT SAVED WILL BE LOST.") / 2, 200, "ALL WORK NOT SAVED WILL BE LOST.");
				font.drawString(Game.getWidth() / 2 - font.getWidth("ARE YOU SURE YOU WANT TO EXIT?") / 2, 225, "ARE YOU SURE YOU WANT TO EXIT?");
				for(Button b : exitButtons) {
					b.draw();
				}
			} else {
				font.drawString(Game.getWidth() / 2 - font.getWidth("PAUSED") / 2, 200, "PAUSED");
				for(Button b : buttons) {
					b.draw();
				}
			}
		}
		
	}
	
	public void mousePressed() {
		
		if(exitConfirmation) {
			int id = -1;
			for(Button b : exitButtons) {
				if(b.isMouseOver()) {
					id = b.getID();
				}
			}
			
			if(id == 0) GameLoop.stop();
			else if(id == 1) exitConfirmation = false;
			
		} else {
			int id = -1;
			for(Button b : buttons) {
				if(b.isMouseOver()) {
					id = b.getID();
				}
			}
			
			switch(id) {
			case CONTINUE:
				hide();
				break;
			case SAVE_CHUNKS:
				Onion.saveChunks();
				break;
			case EXIT:
				exitConfirmation = true;
				break;
			}
		}
	}
	
	public void show() {
		Mouse.setGrabbed(false);
		Mouse.setCursorPosition(Game.getWidth() / 2, Game.getHeight() / 2);
		show = true;
	}
	
	public void hide() {
		if(exitConfirmation) exitConfirmation = false;
		else {
			Mouse.setGrabbed(true);
			show = false;
		}
	}
	
	public boolean isShown() {
		return show;
	}
	
}
