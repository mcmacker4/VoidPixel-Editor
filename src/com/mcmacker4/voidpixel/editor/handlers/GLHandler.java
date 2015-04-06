package com.mcmacker4.voidpixel.editor.handlers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import com.mcmacker4.voidpixel.editor.main.Game;

public class GLHandler {
	
	private static float fov, asp, near, far;
	
	public static void init(float fov, float asp, float near, float far) {
		
		GLHandler.fov = fov;
		GLHandler.asp = asp;
		GLHandler.near = near;
		GLHandler.far = far;
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Game.getWidth(), Game.getHeight());
		gluPerspective(fov, asp, near, far);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glClearColor(0.8f, 0.9f, 1f, 1f);
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_CULL_FACE);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	public static void disableOrtho() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, asp, near, far);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_DEPTH_TEST);
	}
	
	public static void enableOrtho() {
		glDisable(GL_DEPTH_TEST);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Game.getWidth(), Game.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
}
