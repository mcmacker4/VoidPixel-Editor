package com.mcmacker4.voidpixel.editor.world;

import com.mcmacker4.voidpixel.editor.gui.Inventory;
import com.mcmacker4.voidpixel.editor.main.GameLoop;
import com.mcmacker4.voidpixel.editor.material.Material;
import com.mcmacker4.voidpixel.editor.material.Materials;
import com.mcmacker4.voidpixel.editor.util.Vector3;
import com.mcmacker4.voidpixel.editor.view.Camera;

public class BlockHandler {
	
	public static void destroyBlock() {
		
		if(!GameLoop.isPaused()) {
		
			Vector3 obj = new Vector3(Camera.pos.x, Camera.pos.y, Camera.pos.z);
			Onion o = GameLoop.onion;
			
			while(Vector3.getSub(obj, Camera.pos).module() < 100) {
				
				if(o.getBlockAt(-obj.x, -obj.y, -obj.z).isSolid()) {
					o.getBlockAt(-obj.x, -obj.y, -obj.z).setMaterial(Materials.AIR);
					Onion.fixFaces(-obj.x, -obj.z);
					Onion.changed();
					return;
				}
				
				Vector3 forwDiv = new Vector3(Camera.lookAt().x * 0.01f, Camera.lookAt().y * 0.01f, Camera.lookAt().z * 0.01f);
				obj.add(forwDiv);
				
			}
		
		}
		
	}
	
	public static void putBlock() {
		
		if(!GameLoop.isPaused()) {
		
			Vector3 obj = new Vector3(Camera.pos.x, Camera.pos.y, Camera.pos.z);
			Onion o = GameLoop.onion;
			
			while(Vector3.getSub(obj, Camera.pos).module() < 100) {
				
				Vector3 forwDiv = new Vector3(Camera.lookAt().x * 0.01f, Camera.lookAt().y * 0.01f, Camera.lookAt().z * 0.01f);
				
				obj.x += forwDiv.x;
				double valX = forwDiv.x / Math.abs(forwDiv.x);
				if(o.getBlockAt(-obj.x, -obj.y, -obj.z).isSolid()) {
					o.getBlockAt(-obj.x + valX, -obj.y, -obj.z).setMaterial(Inventory.getSelected());
					Onion.fixFaces(-obj.x + valX, -obj.z);
					Onion.changed();
					return;
				}
				
				obj.y += forwDiv.y;
				double valY = forwDiv.y / Math.abs(forwDiv.y);
				if(o.getBlockAt(-obj.x, -obj.y, -obj.z).isSolid()) {
					o.getBlockAt(-obj.x, -obj.y + valY, -obj.z).setMaterial(Inventory.getSelected());
					Onion.fixFaces(-obj.x, -obj.z);
					Onion.changed();
					return;
				}
				
				obj.z += forwDiv.z;
				double valZ = forwDiv.z / Math.abs(forwDiv.z);
				if(o.getBlockAt(-obj.x, -obj.y, -obj.z).isSolid()) {
					o.getBlockAt(-obj.x, -obj.y, -obj.z + valZ).setMaterial(Inventory.getSelected());
					Onion.fixFaces(-obj.x, -obj.z + valZ);
					Onion.changed();
					return;
				}
				
			}
			
		}
		
	}
	
	public static Material getLookAtMaterial() {
		
		Vector3 obj = new Vector3(Camera.pos.x, Camera.pos.y, Camera.pos.z);
		Onion o = GameLoop.onion;
		
		while(Vector3.getSub(obj, Camera.pos).module() < 100) {
			
			if(o.getBlockAt(-obj.x, -obj.y, -obj.z).isSolid()) {
				return o.getBlockAt(-obj.x, -obj.y, -obj.z).getMaterial();
			}
			
			Vector3 forwDiv = new Vector3(Camera.lookAt().x * 0.01f, Camera.lookAt().y * 0.01f, Camera.lookAt().z * 0.01f);
			obj.add(forwDiv);
			
		}
		
		return Materials.AIR;
		
	}
	
}
