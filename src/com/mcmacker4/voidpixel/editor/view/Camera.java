package com.mcmacker4.voidpixel.editor.view;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mcmacker4.voidpixel.editor.main.GameLoop;
import com.mcmacker4.voidpixel.editor.util.Timer;
import com.mcmacker4.voidpixel.editor.util.Vector3;

import static org.lwjgl.opengl.GL11.*;

public class Camera {
	
	public static Vector3 pos = new Vector3(-16, -32, 8);
	public static Vector3 rot = new Vector3(45, 225, 0);
	
	private static float speed = 0.01f;
	
	public static void useView() {
		
		if(!GameLoop.isPaused() && Display.isActive()) {
			rot.x -= Mouse.getDY() * 0.15f;
			rot.y += Mouse.getDX() * 0.15f;
		}
		
		if(rot.x > 90) rot.x = 90;
		if(rot.x < -90) rot.x = -90;
		
//		System.out.println(pos.x + " " + pos.y + " " + pos.z);
		
		if(rot.y > 360) rot.y -= 360;
		if(rot.y < 0) rot.y += 360;
		
		glLoadIdentity();
		glRotated(rot.x, 1, 0, 0);
		glRotated(rot.y, 0, 1, 0);
		glTranslated(pos.x, pos.y, pos.z);
		
//		System.out.println(pos.x + " " + pos.y + " " + pos.z + " " + rot.x + " " + rot.y);
		
	}
	
	public static Vector3 forward() {
		
		Vector3 cam = new Vector3(0, 0, 1);
		
//		cam.rotateX(Math.toRadians(-rot.x));
		cam.rotateY(Math.toRadians(-rot.y));
		
		return cam;
		
	}
	
	public static Vector3 lookAt() {
		Vector3 cam = new Vector3(0, 0, 1);
		
		cam.rotateX(Math.toRadians(-rot.x));
		cam.rotateY(Math.toRadians(-rot.y));
		
		return cam;
	}
	
	public static void goForward() {
		double x = forward().x;
//		double y = forward().y;
		double z = forward().z;
		
		x *= speed;
//		y *= speed;
		z *= speed;
		
		int delta = Timer.getCurrentDelta();
		
		pos.x += x * delta;
//		pos.y += y * delta;
		pos.z += z * delta;
	}
	
	public static void goBackward() {
		double x = forward().x;
//		double y = forward().y;
		double z = forward().z;
		
		x *= speed;
//		y *= speed;
		z *= speed;
		
		int delta = Timer.getCurrentDelta();
		
		pos.x -= x * delta;
//		pos.y -= y * delta;
		pos.z -= z * delta;
	}
	
	public static void goLeft() {
		
		rot.y += 90;
		
		double x = forward().x;
//		double y = forward().y;
		double z = forward().z;
		
		x *= speed;
		z *= speed;
		
		int delta = Timer.getCurrentDelta();
		
		pos.x -= x * delta;
		pos.z -= z * delta;
		
		rot.y -= 90;
		
	}
	
	public static void goRight() {
		
		rot.y -= 90;
		
		double x = forward().x;
//		double y = forward().y;
		double z = forward().z;
		
		x *= speed;
		z *= speed;
		
		int delta = Timer.getCurrentDelta();
		
		pos.x -= x * delta;
		pos.z -= z * delta;
		
		rot.y += 90;
		
	}
	
	public static void goDown() {
		
		int delta = Timer.getCurrentDelta();
		pos.y += speed * delta;
		
	}
	
	public static void goUp() {
		
		int delta = Timer.getCurrentDelta();
		pos.y -= speed * delta;
		
	}
	
}
