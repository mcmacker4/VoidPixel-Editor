package com.mcmacker4.voidpixel.editor.world;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;

import com.mcmacker4.voidpixel.editor.material.Materials;
import com.mcmacker4.voidpixel.editor.material.Textures;

public class Onion {
	
	public static int DIMENSION;
	private static boolean hasChanged = true;
	private static int list;
	private static Chunk[][] chunks;
	
	public Onion(boolean createChunks, int size) {
		
		if(createChunks) {
			DIMENSION = size;
			chunks = new Chunk[DIMENSION][DIMENSION];
			for(int i = 0; i < DIMENSION; i++) {
				for(int j = 0; j < DIMENSION; j++) {
					chunks[i][j] = new Chunk(i, j);
				}
			}
		} else {
			JsonLoader.loadChunks();
			DIMENSION = (int) Math.sqrt(ChunkStorage.storage.size());
			
			chunks = new Chunk[DIMENSION][DIMENSION];
			
			for(Chunk c : ChunkStorage.storage) {
				chunks[Math.abs(c.X)][Math.abs(c.Z)] = c;
			}
			
		}
		setDrawFaces();
	}
	
	public void genCallList() {
		if(hasChanged) {
			glDeleteLists(list, 1);
			list = glGenLists(1);
			glNewList(list, GL_COMPILE);
			for(int i = 0; i < DIMENSION; i++) {
				for(int j = 0; j < DIMENSION; j++) {
					chunks[i][j].draw();
				}
			}
			glEndList();
			hasChanged = false;
		}
	}
	
	public void draw() {
		Textures.GENERIC.bind();
		glCallList(list);
	}
	
	public static void setDrawFaces() {
		
		for(int i = 0; i < DIMENSION; i++) {
			for(int k = 0; k < DIMENSION; k++) {
				chunks[i][k].setRenderFaces();
			}
		}
		
	}
	
	public static void fixFaces(double x, double z) {
		int X = (int) (x>=0 ? x/Chunk.BLOCKS_X : x/Chunk.BLOCKS_X-1);
		int Z = (int) (z>=0 ? z/Chunk.BLOCKS_Z : z/Chunk.BLOCKS_Z-1);
		
		for(int i = 0; i < DIMENSION; i++) {
			for(int k = 0; k < DIMENSION; k++) {
				if(chunks[i][k].X == X && chunks[i][k].Z == Z) {
					chunks[i][k].setRenderFaces();
					return;
				}
			}
		}
	}
	
	public Block getBlockAt(double x, double y, double z) {
		
		if(y < 0 || y >= Chunk.BLOCKS_Y) return new Block(Materials.AIR);
		
		int X = (int) (x>=0 ? x/Chunk.BLOCKS_X : x/Chunk.BLOCKS_X-1);
		int Z = (int) (z>=0 ? z/Chunk.BLOCKS_Z : z/Chunk.BLOCKS_Z-1);
		
		int xx = (int) (x%Chunk.BLOCKS_X>=0 ? x%Chunk.BLOCKS_X : Chunk.BLOCKS_X+x%Chunk.BLOCKS_X);
		int zz = (int) (z%Chunk.BLOCKS_Z>=0 ? z%Chunk.BLOCKS_Z : Chunk.BLOCKS_Z+z%Chunk.BLOCKS_Z);
		
		for(int i = 0; i < DIMENSION; i++) {
			for(int k = 0; k < DIMENSION; k++) {
				if(chunks[i][k].X == X && chunks[i][k].Z == Z) {
					return chunks[i][k].getBlockAt(xx, y, zz);
				}
			}
		}
		
		return new Block(Materials.AIR);
		
	}
	
	public static void loadChunks() {
		DIMENSION = (int) Math.sqrt(ChunkStorage.storage.size());
		chunks = new Chunk[DIMENSION][DIMENSION];
		JsonLoader.loadChunks();
		for(Chunk c : ChunkStorage.storage) {
			chunks[Math.abs(c.X)][Math.abs(c.Z)] = c;
		}
		setDrawFaces();
	}
	
	public static void saveChunks() {
		
		ChunkStorage.storage.clear();
		
		for(int i = 0; i < DIMENSION; i++) {
			for(int j = 0; j < DIMENSION; j++) {
				ChunkStorage.storage.add(chunks[i][j]);
			}
		}
		
		JsonLoader.saveChunks();
		
	}
	
	public static void changed() {
		hasChanged = true;
	}
	
}
