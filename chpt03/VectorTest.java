package chpt03;

class Vector {
	private double Vec_x1;
	private double Vec_x2;
	
	Vector(double x1, double x2){
		this.Vec_x1 = x1;
		this.Vec_x2 = x2;
	}
	
	Vector(Vector V){
		this.Vec_x1 = V.Vec_x1;
		this.Vec_x2 = V.Vec_x2;
	}
	
	public double distance(Vector V) {
		return Math.sqrt(Math.pow(Vec_x1 - V.Vec_x1,  2) + Math.pow(Vec_x2 - V.Vec_x2, 2));
	}
	
	public Vector Scale(double f) {
		this.Vec_x1 *= f;
		this.Vec_x2 *= f;
		return this;
	}
	
	public Vector Add(Vector V) {
		return new Vector(this.Vec_x1 + V.Vec_x1, this.Vec_x2 + V.Vec_x2);
	}
	
	public String toString() {
		return "The Vector2D Info: Vec_x1 = " + this.Vec_x1 + " Vec_x2 = " + this.Vec_x2; 
	}
}

public class VectorTest {
	public static void main(String []args) {
		Vector v1 = new Vector(1, 2);
		Vector v2 = new Vector(v1);
		v1.Scale(3.2);
		Vector v3;
		v3 = v1.Add(v2);
		System.out.println(v1.distance(v2));
		System.out.println(v3);
	}
}
