package com.mcmacker4.voidpixel.editor.util;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontLoader {
	
	public static final TrueTypeFont MAIN_FONT = FontLoader.get("res/font.ttf", 34);
	public static final TrueTypeFont FIELD_FONT = FontLoader.get("res/font.ttf", 24);
	
//	public static final TrueTypeFont MAIN_FONT = FontLoader.get("res/font.ttf", 24);
//	public static final TrueTypeFont FIELD_FONT = FontLoader.get("res/font.ttf", 14);

	public static TrueTypeFont get(String s, float size) {
		
		TrueTypeFont font = null;
		
		try {
			
			InputStream in = ResourceLoader.getResourceAsStream(s);
			Font awt = Font.createFont(Font.TRUETYPE_FONT, in);
			awt = awt.deriveFont(size);
			font = new TrueTypeFont(awt, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return font;
		
	}
	
}
