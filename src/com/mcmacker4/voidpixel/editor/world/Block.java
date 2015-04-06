package com.mcmacker4.voidpixel.editor.world;

import com.google.gson.annotations.Expose;
import com.mcmacker4.voidpixel.editor.material.Material;

public class Block {

	@Expose
	public Material material;
	@Expose
	public boolean voidness;
	
	private boolean[] faces = new boolean[6];
	
	public Block() {
		
	}
	
	public Block(Material material) {
		this.material = material;
	}
	
	public void draw(int x, int y, int z) {
		if(isSolid()) {
			Renderer.cube(x, y, z, material, faces);
		}
	}
	
	public void setFaceRender(int face, boolean should) {
		faces[face] = should;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public boolean isSolid() {
		if(material.material.equalsIgnoreCase("AIR")) return false;
		if(material.material.equalsIgnoreCase("DEFAULT")) return false;
		return true;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
