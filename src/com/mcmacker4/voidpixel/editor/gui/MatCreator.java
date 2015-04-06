package com.mcmacker4.voidpixel.editor.gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

import com.mcmacker4.voidpixel.editor.main.Game;
import com.mcmacker4.voidpixel.editor.material.Material;
import com.mcmacker4.voidpixel.editor.material.Textures;
import com.mcmacker4.voidpixel.editor.util.Button;
import com.mcmacker4.voidpixel.editor.util.Slider;
import com.mcmacker4.voidpixel.editor.util.TextField;

public class MatCreator {
	
	private boolean show;
	private Inventory inv;
	
	private Texture tex = Textures.GENERIC;
	
	private TextField nameField;
	private Button create;
	private Button cancel;
	private Slider red;
	private Slider green;
	private Slider blue;

	public MatCreator(Inventory inv) {
		this.inv = inv;
		float sx = 200;
		float fx = Game.getWidth() / 2 - sx / 2;
		float fy = 250;
		nameField = new TextField(fx, fy, sx);
		
		red = new Slider(Color.red, 300);
		green = new Slider(Color.green, 330);
		blue = new Slider(Color.blue, 360);
		
		create = new Button("create", 0, 400, 0);
		create.centerHorizontal();
		
		cancel = new Button("cancel", 0, 430, 1);
		cancel.centerHorizontal();
	}
	
	public void draw() {
		if(show) {
			drawPreview();
			red.draw();
			green.draw();
			blue.draw();
			create.draw();
			cancel.draw();
			nameField.draw();
		}
	}
	
	private void drawPreview() {
		
		glPushMatrix();
			tex.bind();
			glColor3f(red.getVal(), green.getVal(), blue.getVal());
			glTranslatef(Game.getWidth() / 2 - 64, 50, 0);
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(0, 0);
				glTexCoord2f(0, 1);
				glVertex2f(0, 128);
				glTexCoord2f(1, 1);
				glVertex2f(128, 128);
				glTexCoord2f(1, 0);
				glVertex2f(128, 0);
			glEnd();
		glPopMatrix();
		
	}
	
	public void createMaterial() {
		Material mat = new Material(nameField.getText().replaceAll(" ", "_").toUpperCase(), red.getVal(), green.getVal(), blue.getVal());
		inv.addMaterial(mat);
		hide();
	}
	
	public void keyPressed() {
		if(Keyboard.getEventKey() == Keyboard.KEY_BACK) {
			nameField.deleteChar();
		} else {
			char c = Keyboard.getEventCharacter();
			nameField.addChar(c);
		}
	}
	
	public void mousePressed() {
		if(nameField.isMouseOver()) {
			nameField.setSelected(true);
		} else {
			nameField.setSelected(false);
		}
		if(create.isMouseOver()) {
			createMaterial();
		}
		if(cancel.isMouseOver()) {
			hide();
		}
		red.mousePressed();
		green.mousePressed();
		blue.mousePressed();
	}
	
	public void mouseReleased() {
		red.mouseReleased();
		green.mouseReleased();
		blue.mouseReleased();
	}
	
	public boolean canHide() {
		return !nameField.isSelected();
	}
	
	public void show() {
		show = true;
	}
	
	public void hide() {
		nameField.setSelected(false);
		nameField.reset();
		red.reset();
		green.reset();
		blue.reset();
		show = false;
	}
	
	public boolean isShown() {
		return show;
	}
	
}
