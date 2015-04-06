package com.mcmacker4.voidpixel.editor.util;

import org.lwjgl.input.Mouse;

import com.mcmacker4.voidpixel.editor.main.Game;
import com.mcmacker4.voidpixel.editor.material.Material;
import com.mcmacker4.voidpixel.editor.material.Textures;

import static org.lwjgl.opengl.GL11.*;

public class MaterialButton {
	
	private float x, y;
	private int size;
	private Material mat;
	private boolean selected;
	
	private int ID;
	
	public MaterialButton(Material mat, int size, int ID) {
		this.mat = mat;
		this.size = size;
		this.ID = ID;
	}
	
	public void draw(float x, float y) {
		
		this.x = x;
		this.y = y;
		
		glPushMatrix();
			
			Textures.GENERIC.bind();
			glTranslatef(x, y, 0);
			glColor3f(mat.getRed(), mat.getGreen(), mat.getBlue());
			
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(2, 2);
				glTexCoord2f(0, 1);
				glVertex2f(2, size - 2);
				glTexCoord2f(1, 1);
				glVertex2f(size - 2, size - 2);
				glTexCoord2f(1, 0);
				glVertex2f(size - 2, 2);
			glEnd();
			
			if(isMouseOver() || selected) {
				
				Textures.TILE_SELECTED.bind();
				glColor3f(1f, 1f, 1f);
				
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2f(0, 0);
					glTexCoord2f(0, 1);
					glVertex2f(0, size);
					glTexCoord2f(1, 1);
					glVertex2f(size, size);
					glTexCoord2f(1, 0);
					glVertex2f(size, 0);
				glEnd();
				
			}
		
		glPopMatrix();
		
	}
	
	public boolean isMouseOver() {
		float mx = Mouse.getX();
		float my = Game.getHeight() - Mouse.getY();
		
		return mx > x && mx < x + size && my > y && my < y + size;
	}
	
	public Material getMaterial() {
		return mat;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
}
