package com.mcmacker4.voidpixel.editor.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.mcmacker4.voidpixel.editor.handlers.GLHandler;
import com.mcmacker4.voidpixel.editor.main.Game;

public class GUI {
	
	private static PauseMenu pauseMenu;
	private static Inventory inventory;
	private static Debug debug;
	
	public GUI() {
		pauseMenu = new PauseMenu();
		inventory = new Inventory();
		debug = new Debug();
	}
	
	public void draw() {
		
		GLHandler.enableOrtho();
		
		if(isPaused())
			dimmBackground();
		else {
			if(!Mouse.isInsideWindow()) Mouse.setCursorPosition(Game.getWidth() / 2, Game.getHeight() / 2);
			drawPointer();
			drawCurrentMaterial();
			debug.draw();
		}
		
		pauseMenu.draw();
		inventory.draw();
		
		GLHandler.disableOrtho();
		
	}
	
	private void drawCurrentMaterial() {
		glColor4f(1f - Inventory.selected.getRed() + 0.2f, 1f - Inventory.selected.getGreen() + 0.2f, 1f - Inventory.selected.getBlue() + 0.2f, 1f);
		glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(0, inventory.font.getHeight());
			glVertex2f(inventory.font.getWidth(Inventory.selected.material) + 8, inventory.font.getHeight());
			glVertex2f(inventory.font.getWidth(Inventory.selected.material) + 8, 0);
		glEnd();
		
		inventory.font.drawString(5, 0, Inventory.selected.material, new Color(Inventory.selected.getRed(), Inventory.selected.getGreen(), Inventory.selected.getBlue()));
	}
	
	public static void mousePressed() {
		if(pauseMenu.isShown()) {
			pauseMenu.mousePressed();
		} else if(inventory.isShown()) {
			inventory.mousePressed();
		}
	}
	
	public static void mouseReleased() {
		if(inventory.isShown()) {
			inventory.mouseReleased();
		}
	}
	
	public static void keyPressed() {
		inventory.keyPressed();
	}
	
	public static void togglePause() {
		if(!pauseMenu.isShown() && !inventory.isShown()) {
			pauseMenu.show();
		} else if(pauseMenu.isShown()) {
			pauseMenu.hide();
		} else if(inventory.isShown()) {
			inventory.getCreator().hide();
			inventory.hide();
		}
	}
	
	public static void toggleInventory() {
		if(!inventory.isShown() && !pauseMenu.isShown()) {
			inventory.show();
		} else if(inventory.isShown()) {
			if(inventory.getCreator().isShown()) {
				if(inventory.getCreator().canHide()) {
					inventory.getCreator().hide();
				}
			} else {
				inventory.hide();
			}
		}
	}
	
	public static void toggleDebug() {
		if(debug.isShown()) debug.hide();
		else debug.show();
	}
	
	public boolean isPaused() {
		return inventory.isShown() || pauseMenu.isShown();
	}
	
	private void dimmBackground() {
		glColor4f(0f, 0f, 0f, 0.6f);
		glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(0, Game.getHeight());
			glVertex2f(Game.getWidth(), Game.getHeight());
			glVertex2f(Game.getWidth(), 0);
		glEnd();
	}
	
	private void drawPointer() {
		
		int size = 4;
		float xPos = Game.getWidth() / 2 - size / 2;
		float yPos = Game.getHeight() / 2 - size / 2;
		
		glPushMatrix();
			glTranslatef(xPos, yPos, 0);
			glColor3f(1f, 1f, 1f);
			glBegin(GL_QUADS);
				glVertex2f(0, 0);
				glVertex2f(0, size);
				glVertex2f(size, size);
				glVertex2f(size, 0);
			glEnd();
		glPopMatrix();
		
	}
	
}
