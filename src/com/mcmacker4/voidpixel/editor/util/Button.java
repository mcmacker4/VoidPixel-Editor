package com.mcmacker4.voidpixel.editor.util;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.mcmacker4.voidpixel.editor.main.Game;

public class Button {
	
	private String text;
	private int x, y;
	
	private final Color normalColor = Color.cyan;
	private final Color overColor = Color.blue;
	
	private TrueTypeFont font = FontLoader.MAIN_FONT;
	
	private int ID;
	
	private boolean center;
	
	public Button(String text, int x, int y, int ID) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.ID = ID;
	}
	
	public void draw() {
		Color c;
		
		if(isMouseOver()) c = overColor;
		else c = normalColor;
		
		font.drawString(x, y, text, c);
	}
	
	public boolean isMouseOver() {
		
		float mx = Mouse.getX();
		float my = Game.getHeight() - Mouse.getY();
		
		return mx > x && mx < x + font.getWidth(text) && my > y && my < y + font.getHeight();
		
	}
	
	public void centerHorizontal() {
		center = true;
		x = Game.getWidth() / 2 - font.getWidth(text) / 2;
	}
	
	public void setText(String s) {
		text = s;
		if(center) centerHorizontal();
	}
	
	public String getText() {
		return text;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
}
