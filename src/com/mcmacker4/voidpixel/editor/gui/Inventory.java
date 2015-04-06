package com.mcmacker4.voidpixel.editor.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import com.mcmacker4.voidpixel.editor.main.Game;
import com.mcmacker4.voidpixel.editor.material.Material;
import com.mcmacker4.voidpixel.editor.material.Materials;
import com.mcmacker4.voidpixel.editor.util.Button;
import com.mcmacker4.voidpixel.editor.util.FontLoader;
import com.mcmacker4.voidpixel.editor.util.MaterialButton;
import com.mcmacker4.voidpixel.editor.world.BlockHandler;

public class Inventory {
	private boolean show;
	
	private Button createButton;
	private Button deleteButton;
	private static ArrayList<MaterialButton> matButtons = new ArrayList<>();
	private static int tileSize = 64;
	private int maxTiles = 70;
	private int tileStartY = 64;
	
	private MatCreator creator = new MatCreator(this);
	
	private static int selectedID = 0;
	public static Material selected;
	TrueTypeFont font = FontLoader.MAIN_FONT;
	
	public Inventory() {
		
		createButton = new Button("Create", 0, Game.getHeight() - 55, 0);
		createButton.centerHorizontal();
		deleteButton = new Button("Delete", 0, Game.getHeight() - 30, 0);
		deleteButton.centerHorizontal();
		
		for(int i = 0; i < Materials.all.length; i++) {
			matButtons.add(new MaterialButton(Materials.all[i], tileSize, i));
		}
		
		selected = matButtons.get(selectedID).getMaterial();
		
	}
	
	public void draw() {
		if(show && !creator.isShown()) {
			
			createButton.draw();
			deleteButton.draw();
			
			for(int i = 0; i < matButtons.size(); i++) {
				matButtons.get(i).setSelected(false);
			}
			matButtons.get(selectedID).setSelected(true);
			selected = matButtons.get(selectedID).getMaterial();
			
			font.drawString(Game.getWidth() / 2 - font.getWidth("MATERIAL SELECTION") / 2, 32 - font.getHeight() / 2 - 20, "MATERIAL SELECTION");
			
			String nameSelected = "";
			for(MaterialButton b : matButtons) {
				if(b.isMouseOver()) nameSelected = b.getMaterial().material;
			}
			String correctName = nameSelected.replace("_", " ");
			font.drawString(Game.getWidth() / 2 - font.getWidth(correctName) / 2, 32 - font.getHeight() / 2 + 15, correctName);
			
			float x = 0;
			float y = tileStartY;
			for(int i = 0; i < matButtons.size(); i++) {
				matButtons.get(i).draw(x, y);
				x += tileSize;
				if(x > Game.getWidth() - tileSize) {
					x = 0;
					y += tileSize;
				}
			}
		} else if(creator.isShown()) {
			creator.draw();
		}
	}
	
	public void mousePressed() {
		if(!creator.isShown()) {
			for(MaterialButton b : matButtons) {
				if(b.isMouseOver()) {
					selectedID = b.getID();
				}
			}
			if(createButton.isMouseOver() && matButtons.size() < maxTiles) {
				creator.show();
			}
			if(deleteButton.isMouseOver()) {
				deleteMaterial();
			}
		} else {
			creator.mousePressed();
		}
	}
	
	public void mouseReleased() {
		if(creator.isShown()) {
			creator.mouseReleased();
		}
	}
	
	public void keyPressed() {
		creator.keyPressed();
	}
	
	private void deleteMaterial() {
		matButtons.remove(selectedID);
		selectedID = 0;
		for(int i = 0; i < matButtons.size(); i++) {
			matButtons.get(i).setID(i);
		}
	}
	
	public static Material getSelected() {
		return selected;
	}
	
	public static void setSelected() {
		
		Material mat = BlockHandler.getLookAtMaterial();
		
		if(!mat.equals(Materials.AIR)) {
			
			for(int i = 0; i < matButtons.size(); i++) {
				if(matButtons.get(i).getMaterial().material.equalsIgnoreCase(mat.material)
						&& matButtons.get(i).getMaterial().getRed() == mat.getRed()
						&& matButtons.get(i).getMaterial().getGreen() == mat.getGreen()
						&& matButtons.get(i).getMaterial().getBlue() == mat.getBlue()) {
					
					selectedID = i;
					selected = matButtons.get(i).getMaterial();
					matButtons.get(i).setSelected(true);
					return;
				} else {
					matButtons.get(i).setSelected(false);
				}
			}
			
			int id = matButtons.size();
			matButtons.add(new MaterialButton(mat, tileSize, id));
			selected = mat;
			selectedID = id;
			
		}
		
	}
	
	public void addMaterial(Material mat) {
		matButtons.add(new MaterialButton(mat, tileSize, matButtons.size()));
		selectedID = matButtons.size() - 1;
	}
	
	public void show() {
		Mouse.setCursorPosition(Game.getWidth() / 2, Game.getHeight() / 2);
		Mouse.setGrabbed(false);
		show = true;
	}
	
	public void hide() {
		Mouse.setCursorPosition(Game.getWidth() / 2, Game.getHeight() / 2);
		Mouse.setGrabbed(true);
		show = false;
	}
	
	public boolean isShown() {
		return show;
	}
	
	public MatCreator getCreator() {
		return creator;
	}
	
}
