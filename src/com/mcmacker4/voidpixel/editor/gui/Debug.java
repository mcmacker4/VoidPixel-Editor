package com.mcmacker4.voidpixel.editor.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.mcmacker4.voidpixel.editor.handlers.GLHandler;
import com.mcmacker4.voidpixel.editor.util.FontLoader;
import com.mcmacker4.voidpixel.editor.util.Timer;
import com.mcmacker4.voidpixel.editor.view.Camera;

import static org.lwjgl.opengl.GL11.*;

public class Debug {
	
	private boolean show;
	
	private TrueTypeFont font = FontLoader.FIELD_FONT;
	
	public void draw() {
		if(show) {
			
			font.drawString(20, 30, "FPS: " + Timer.getFPS(), Color.magenta);
			font.drawString(20, 50, "Camera:", Color.magenta);
			font.drawString(40, 70, "Position: " + Camera.pos.negated().toString(), Color.magenta);
			font.drawString(40, 90, "Rotation: " + Camera.rot.toString(), Color.magenta);
			
			GLHandler.disableOrtho();
			glBegin(GL_LINES);
				glColor4f(1f, 0f, 0f, 1f);
				glVertex3f(0, 0, 0);
				glVertex3f(200, 0, 0);
				
				glColor4f(0f, 1f, 0f, 1f);
				glVertex3f(0, 0, 0);
				glVertex3f(0, 200, 0);
				
				glColor4f(0f, 0f, 1f, 1f);
				glVertex3f(0, 0, 0);
				glVertex3f(0, 0, 200);
			glEnd();
			GLHandler.enableOrtho();
			
		}
	}
	
	public void show() {
		show = true;
	}
	
	public void hide() {
		show = false;
	}
	
	public boolean isShown() {
		return show;
	}
	
}
