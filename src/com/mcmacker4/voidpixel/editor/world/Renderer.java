package com.mcmacker4.voidpixel.editor.world;

import static org.lwjgl.opengl.GL11.*;

import com.mcmacker4.voidpixel.editor.material.Material;

public class Renderer {

	public static void cube(float x, float y, float z, Material mat, boolean[] render) {
		
		glPushMatrix();
		
			glColor3f(mat.colorMaterial[0], mat.colorMaterial[1], mat.colorMaterial[2]);
			glTranslatef(x, y, z);
			
			glBegin(GL_QUADS);
				
				//0 Front == Z+
				if(render[Faces.FRONT]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(0, 0, 0);
					glTexCoord2f(0, 0);
					glVertex3f(0, 1, 0);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 0);
					glTexCoord2f(1, 1);
					glVertex3f(1, 0, 0);
				}
		
				//1 Back == Z-
				if(render[Faces.BACK]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(0, 0, 1);
					glTexCoord2f(0, 0);
					glVertex3f(1, 0, 1);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 1);
					glTexCoord2f(1, 1);
					glVertex3f(0, 1, 1);
				}
		
				//2 Top == Y+
				if(render[Faces.TOP]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(0, 1, 0);
					glTexCoord2f(0, 0);
					glVertex3f(0, 1, 1);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 1);
					glTexCoord2f(1, 1);
					glVertex3f(1, 1, 0);
				}
	
				//3 Bottom == Y-
				if(render[Faces.BOTTOM]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(0, 0, 0);
					glTexCoord2f(0, 0);
					glVertex3f(1, 0, 0);
					glTexCoord2f(1, 0);
					glVertex3f(1, 0, 1);
					glTexCoord2f(1, 1);
					glVertex3f(0, 0, 1);
				}
				
				//5 Right == X+
				if(render[Faces.RIGHT]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(1, 0, 0);
					glTexCoord2f(0, 0);
					glVertex3f(1, 1, 0);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 1);
					glTexCoord2f(1, 1);
					glVertex3f(1, 0, 1);
				}
		
				//4 Left == X-
				if(render[Faces.LEFT]) {
					glFrontFace(GL_CW);
					glPolygonMode(GL_FRONT, GL_FILL);
					glTexCoord2f(0, 1);
					glVertex3f(0, 0, 0);
					glTexCoord2f(0, 0);
					glVertex3f(0, 0, 1);
					glTexCoord2f(1, 0);
					glVertex3f(0, 1, 1);
					glTexCoord2f(1, 1);
					glVertex3f(0, 1, 0);
				}
		
			glEnd();
		
		glPopMatrix();
		
	}
	
}
