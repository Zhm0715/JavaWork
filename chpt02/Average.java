package chpt02;

public class Average {
	public static void main(String []args) {
        int num1 = (int) (Math.random() * 7);
        int num2 = (int) (Math.random() * 7);
        System.out.println("The First die comes up " + num1);
        System.out.println("The First die comes up " + num2);
        System.out.println("Your total roll is " + (num1 + num2));
        System.out.println("The average is " + (double)(num1 + num2) / 2);
	}
}
