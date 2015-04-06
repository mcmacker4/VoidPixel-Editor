package com.mcmacker4.voidpixel.editor.material;

public class Materials {
	
	public static final Material DEFAULT = new Material("DEFAULT", 1.0f, 1.0f, 1.0f);
	public static final Material AIR = new Material("AIR", 1.0f, 1.0f, 1.0f);
	public static final Material GRASS = new Material("GRASS", 0.4f, 0.8f, 0.4f);
	public static final Material WATER = new Material("WATER", 0.3f, 0.7f, 0.7f);
	public static final Material STONE = new Material("STONE", 0.5f, 0.5f, 0.5f);
	public static final Material HARD_STONE = new Material("HARD_STONE", 0.3f, 0.3f, 0.3f);
	public static final Material WOOD = new Material("WOOD", 0.369f, 0.329f, 0.329f);
	public static final Material SAND = new Material("SAND", 0.878f, 0.847f, 0.565f);
	public static final Material WEIRD_MATERIAL = new Material("WEIRD_MATERIAL", 1f, 0f, 1f);
	
	public static final Material[] all = new Material[] {
		GRASS, WATER, STONE, HARD_STONE, WOOD, SAND, WEIRD_MATERIAL
	};
	
}
