package com.mcmacker4.voidpixel.editor.material;

import com.google.gson.annotations.Expose;

public class Material {

	@Expose
	public String material;
	@Expose
	public float[] colorMaterial = new float[3];
	
	public Material() {
		
	}
	
	public Material(String material, float r, float g, float b) {
		this.material = material;
		colorMaterial[0] = r;
		colorMaterial[1] = g;
		colorMaterial[2] = b;
	}
	
	public float getRed() {
		return colorMaterial[0];
	}
	
	public float getGreen() {
		return colorMaterial[1];
	}
	
	public float getBlue() {
		return colorMaterial[2];
	}
	
}
