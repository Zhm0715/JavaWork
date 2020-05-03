package chpt03;

public class Triangle {
	private int x1;
	private int x2;
	private int x3;
	
	Triangle(){	}
	
	Triangle(int x, int y, int z){
		this.x1 = x;
		this.x2 = y;
		this.x3 = z;
	}
	
	Triangle(Triangle t){
		this.x1 = t.x1;
		this.x2 = t.x2;
		this.x3 = t.x3;
	}
	
	public String triangleType() {
		int min =  Math.min(this.x1, this.x2);
		min =  Math.min(min, this.x3);
		int max =  Math.max(this.x1, this.x2);
		max =  Math.max(max, this.x3);
		int mid = this.x1 + this.x2 + this.x3 - min - max;
		if(min * min + mid * mid == max * max) {
			return "RightAngled";
		}else if(min + mid == max) {
			return "Flat";
		}else if(min + mid < max) {
			return "Impossible";
		}
		if(this.x1 == this.x2 && this.x1 == this.x3 && this.x1 != 0) {
			return "Equilateral";
		}else if((this.x1 == this.x2 || this.x1 == this.x3 || this.x2 == this.x3) && this.x1 != 0 && this.x2 != 0 && this.x3 != 0) {
			return "Isosceles";
		}
		return "Scalene";
	}
	
	public String toString() {
		return this.triangleType() + "(" 
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
	
	public static void main(String []args) {
		makeTriangles(2);
	}
}
