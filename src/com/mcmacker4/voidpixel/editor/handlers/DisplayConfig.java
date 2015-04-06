package com.mcmacker4.voidpixel.editor.handlers;

import com.google.gson.annotations.Expose;

public class DisplayConfig {
	
	@Expose
	public int WIDTH;
	@Expose
	public int HEIGHT;
	@Expose
	public boolean vsync;
	@Expose
	public boolean fullscreen;
	@Expose
	public float fov;
	
}
