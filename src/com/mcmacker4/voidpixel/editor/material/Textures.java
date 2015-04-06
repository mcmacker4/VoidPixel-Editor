package com.mcmacker4.voidpixel.editor.material;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Textures {

	public static Texture GENERIC;
	public static Texture TILE_SELECTED;
	public static Texture LOGO16;
	public static Texture LOGO32;
	
	public static void loadAll() {
		
		try {
			GENERIC = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textures/generic.png"));
			GENERIC.setTextureFilter(GL_NEAREST);
			
			TILE_SELECTED = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textures/tileSelected.png"));
			TILE_SELECTED.setTextureFilter(GL_NEAREST);
			
			LOGO16 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textures/logo16.png"));
			LOGO16.setTextureFilter(GL_NEAREST);
			
			LOGO32 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textures/logo32.png"));
			LOGO32.setTextureFilter(GL_NEAREST);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
