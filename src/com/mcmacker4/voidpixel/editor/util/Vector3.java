package com.mcmacker4.voidpixel.editor.util;

public class Vector3 {

	public double x,y,z;
	
	public Vector3(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(double[] m){
		x = m[0];
		y = m[1];
		z = m[2];
	}

	public void rotateX(double d) {
		double[][] rot = {{1,0,0},{0,Math.cos(d),-Math.sin(d)},{0,Math.sin(d),Math.cos(d)}};
		double a = tensorial(this, new Vector3(rot[0]));
		double b = tensorial(this, new Vector3(rot[1]));
		double c = tensorial(this, new Vector3(rot[2]));
		Vector3 e = new Vector3(a, b, c);
		e = e.unitaryVector();
		x = e.x;
		y = e.y;
		z = e.z;
	}
	
	public void rotateY(double d) {
		double[][] rot = {{Math.cos(d),0,Math.sin(d)},{0,1,0},{-Math.sin(d),0,Math.cos(d)}};
		double a = tensorial(this, new Vector3(rot[0]));
		double b = tensorial(this, new Vector3(rot[1]));
		double c = tensorial(this, new Vector3(rot[2]));
		Vector3 e = new Vector3(a, b, c);
		e = e.unitaryVector();
		x = e.x;
		y = e.y;
		z = e.z;
	}
	
	public void rotateZ(double d) {
		double[][] rot = {{Math.cos(d),-Math.sin(d),0},{Math.sin(d),Math.cos(d),0},{0,0,1}};
		double a = tensorial(this, new Vector3(rot[0]));
		double b = tensorial(this, new Vector3(rot[1]));
		double c = tensorial(this, new Vector3(rot[2]));
		Vector3 e = new Vector3(a, b, c);
		e = e.unitaryVector();
		x = e.x;
		y = e.y;
		z = e.z;
	}
	
	public void multiply(Vector3 a) {
		x += a.x;
		y += a.y;
		z += a.z;
	}
	
	public void multiply(double a){
		x *= a;
		y *= a;
		z *= a;
	}

	public Vector3 unitaryVector(){
		double mod = module();
		if(mod == 0)return new Vector3(0,0,0);
		return new Vector3(x/mod, y/mod, z/mod);
	}

	public double module() {
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public String toString(){
		return "x: " + Math.round(x) + " y: " + Math.round(y) + " z: " + Math.round(z);
	}
	
	public double tensorial(Vector3 a,Vector3 b){
		return a.x*b.x+a.y*b.y+a.z*b.z;
	}

	public void add(Vector3 a) {
		x += a.x;
		y += a.y;
		z += a.z;
	}
	
	public void sub(Vector3 a) {
		x -= a.x;
		y -= a.y;
		z -= a.z;
	}
	
	public static Vector3 getAdd(Vector3 a, Vector3 b) {
		return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3 getSub(Vector3 a, Vector3 b) {
		return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public void negate() {
		x = -x;
		y = -y;
		z = -z;
	}
	
	public Vector3 negated() {
		return new Vector3(-x, -y, -z);
	}

	public Vector3 copy() {
		return new Vector3(x, y, z);
	}
}