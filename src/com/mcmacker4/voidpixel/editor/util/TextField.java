package com.mcmacker4.voidpixel.editor.util;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.mcmacker4.voidpixel.editor.main.Game;

public class TextField {
	
	private float x, y;
	private float sx, sy;
	private boolean selected;
	
	private String text = "unnamed";
	private TrueTypeFont font = FontLoader.FIELD_FONT;
	
	public TextField(float x, float y, float sx) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = font.getHeight() + 4;
	}
	
	public void draw() {
		
		String toRend;
		if(selected) toRend = text + "|";
		else toRend = text;
		
		font.drawString(x + sx / 2 - font.getWidth("WRITE NAME HERE:") / 2, (y - 20) + sy / 2 - font.getHeight() / 2, "WRITE NAME HERE:", Color.lightGray);
		font.drawString(x + sx / 2 - font.getWidth(toRend) / 2, y + sy / 2 - font.getHeight() / 2, toRend, Color.white);
		
	}
	
	public void addChar(char c) {
		if(selected) {
			if(Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c)) {
				text += c;
			}
		}
	}
	
	public void deleteChar() {
		if(selected && text.length() < 12) {
			char[] chars = text.toCharArray();
			if(chars.length > 0) {
				char[] newChars = new char[chars.length - 1];
				for(int i = 0; i < newChars.length; i++)
					newChars[i] = chars[i];
				text = String.valueOf(newChars);
			}
		}
	}
	
	public String getText() {
		return text;
	}
	
	public void reset() {
		text = "unnamed";
	}
	
	public boolean isMouseOver() {
		float mx = Mouse.getX();
		float my = Game.getHeight() - Mouse.getY();
		
		return mx > x && mx < x + sx && my > y && my < y + sy;
	}
	
	public void setSelected(boolean sel) {
		selected = sel;
		if(sel && text == "unnamed") text = "";
	}
	
	public boolean isSelected() {
		return selected;
	}
	
}
