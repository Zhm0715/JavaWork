package chpt03;

interface TriangleType{
	String Equila = "Equilateral";
	String Isos = "Isosceles";
	String Flat = "Flat";
	String Impos = "Impossible";
	String Scal = "Scalene";
	String RightAng = "RightAngled";
}

class TriangleEum{
	private int x1;
	private int x2;
	private int x3;
	private Type Tritype;
	
	public static enum Type{
		Equila, Isos, Flat, Impos, Scal, RightAng;
	}
	
	TriangleEum(){	}
	
	TriangleEum(int x, int y, int z){
		this.x1 = x;
		this.x2 = y;
		this.x3 = z;
	}
	
	TriangleEum(TriangleEum t){
		this.x1 = t.x1;
		this.x2 = t.x2;
		this.x3 = t.x3;
	}
	
	public void triangleType() {
		int min =  Math.min(this.x1, this.x2);
		min =  Math.min(min, this.x3);
		int max =  Math.max(this.x1, this.x2);
		max =  Math.max(max, this.x3);
		int mid = this.x1 + this.x2 + this.x3 - min - max;
		if(min * min + mid * mid == max * max) {
			this.Tritype = Type.RightAng;
//			return "RightAngled";
		}else if(min + mid == max) {
			this.Tritype = Type.Flat;
//			return "Flat";
		}else if(min + mid < max) {
			this.Tritype = Type.Impos;
//			return "Impossible";
		}
		if(this.x1 == this.x2 && this.x1 == this.x3 && this.x1 != 0) {
			this.Tritype = Type.Equila;
//			return "Equilateral";
		}else if((this.x1 == this.x2 || this.x1 == this.x3 || this.x2 == this.x3) && this.x1 != 0 && this.x2 != 0 && this.x3 != 0) {
			this.Tritype = Type.Isos;
//			return "Isosceles";
		}
//		return "Scalene";
	}
	
	public String toString() {
		return this.Tritype + "(" 
			   + this.x1 + "," + this.x2 + "," + this.x3 + ")";
	}
	
	public static void makeTriangles(int n) {
		for(int i = 1;i <= n;++i) {
			for(int j = 1;j <= n;++j) {
				for(int k = 1;k <= n;++k) {
					Triangle NewTri = new Triangle(i, j, k);
					System.out.println(NewTri);
				}
			}
		}
	}
}


public class EnumExp {
	public static void main(String []args) {
		TriangleEum.makeTriangles(2);
	}
}
