package com.mcmacker4.voidpixel.editor.util;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.mcmacker4.voidpixel.editor.main.Game;

import static org.lwjgl.opengl.GL11.*;

public class Slider {
	
	private float yPos;
	
	private float guideWidth = 200, guideHeight = 4;
	private float guideX = Game.getWidth() / 2 - guideWidth / 2;
	
	private float sliderPos = 0;
	private float sliderWidth = 10, sliderHeight = 20;
	
	private Color c;
	
	private boolean grabbed;
	
	public Slider(Color c, float yPos) {
		this.c = c;
		this.yPos = yPos;
		
		sliderPos = guideWidth / 2;
	}
	
	public void draw() {
		
		if(Mouse.isButtonDown(0) && Mouse.getX() >= guideX - sliderWidth/2 && Mouse.getX() <= guideX + guideWidth + sliderWidth/2  && grabbed) {
			sliderPos += Mouse.getDX();
			if(sliderPos < 0) {
				sliderPos = 0;
			}
			if(sliderPos > guideWidth) {
				sliderPos = guideWidth;
			}
		}
		
		glPushMatrix();
			glColor4f(1f, 1f, 1f, 1f);
			glTranslatef(guideX, yPos - guideHeight/2, 0);
			glBegin(GL_QUADS);
				glVertex2f(0, 0);
				glVertex2f(0, guideHeight);
				glVertex2f(guideWidth, guideHeight);
				glVertex2f(guideWidth, 0);
			glEnd();
		glPopMatrix();
		
		glPushMatrix();
			glColor4f(c.getRed(), c.getGreen(), c.getBlue(), 1f);
			glTranslatef(guideX + sliderPos - sliderWidth/2, yPos - sliderHeight/2, 0);
			glBegin(GL_QUADS);
				glVertex2f(0, 0);
				glVertex2f(0, sliderHeight);
				glVertex2f(sliderWidth, sliderHeight);
				glVertex2f(sliderWidth, 0);
			glEnd();
		glPopMatrix();
		
	}
	
	public float getVal() {
		return sliderPos / guideWidth;
	}
	
	public void mousePressed() {
		if(isMouseOver()) {
			grabbed = true;
		}
	}
	
	public void mouseReleased() {
		grabbed = false;
	}
	
	public void reset() {
		sliderPos = guideWidth / 2;
	}
	
	public boolean isMouseOver() {
		float mx = Mouse.getX();
		float my = Game.getHeight() - Mouse.getY();
		float sliderPosX = guideX + sliderPos - sliderWidth/2;
		float sliderPosY = yPos - sliderHeight / 2;
		
		return mx > sliderPosX && mx < sliderPosX + sliderWidth && my > sliderPosY && my < sliderPosY + sliderHeight;
	}
	
}
