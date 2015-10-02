package com.mcmacker4.voidpixel.editor.world;

import com.google.gson.annotations.Expose;
import com.mcmacker4.voidpixel.editor.material.Materials;

public class Chunk {
	
	public static final int BLOCKS_X = 8;
	public static final int BLOCKS_Y = 32;
	public static final int BLOCKS_Z = 8;

	@Expose
	public int X;
	@Expose
	public int Z;
	@Expose
	public Block[][][] Blocks = new Block[BLOCKS_Y][BLOCKS_Z][BLOCKS_X];
	@Expose
	public boolean createdBlock;
	
	private String fileName;
	
	public Chunk() {
	}
	
	public Chunk(int X, int Z) {
		this.X = X;
		this.Z = Z;
		if(X != 0) X = -X;
		for(int j = 0; j < BLOCKS_Y; j++) {
			for(int k = 0; k < BLOCKS_Z; k++) {
				for(int i = 0; i < BLOCKS_X; i++) {
					Blocks[j][k][i] = new Block(Materials.GRASS);
				}
			}
		}
	}
	
	public void draw() {
		
		for(int j = 0; j < BLOCKS_Y; j++) {
			for(int k = 0; k < BLOCKS_Z; k++) {
				for(int i = 0; i < BLOCKS_X; i++) {
					Blocks[j][k][i].draw(i + X * BLOCKS_X, j, k + Z * BLOCKS_Z);
				}
			}
		}
		
	}
	
	public void setRenderFaces() {
		
		for(int j = 0; j < BLOCKS_Y; j++) {
			for(int k = 0; k < BLOCKS_Z; k++) {
				for(int i = 0; i < BLOCKS_X; i++) {
					
					getBlockAt(i, j, k).setFaceRender(Faces.BACK, !getBlockAt(i, j, k + 1).isSolid());
					getBlockAt(i, j, k).setFaceRender(Faces.FRONT, !getBlockAt(i, j, k - 1).isSolid());
					getBlockAt(i, j, k).setFaceRender(Faces.TOP, !getBlockAt(i, j + 1, k).isSolid());
					getBlockAt(i, j, k).setFaceRender(Faces.BOTTOM, !getBlockAt(i, j - 1, k).isSolid());
					getBlockAt(i, j, k).setFaceRender(Faces.RIGHT, !getBlockAt(i + 1, j, k).isSolid());
					getBlockAt(i, j, k).setFaceRender(Faces.LEFT, !getBlockAt(i - 1, j, k).isSolid());
					
				}
			}
		}
		
	}
	
	public Block getBlockAt(double x, double y, double z) {
		
		try {
			return Blocks[(int) y][(int) z][(int) x];
		} catch(Exception e) {
//			System.out.println(e.getMessage());
		}
		
		return new Block(Materials.AIR);
		
	}
	
	public void setFileName(String s) {
		fileName = s;
	}
	
	public String getFileName() {
		return fileName;
	}
	
}
