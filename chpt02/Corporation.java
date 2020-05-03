package chpt02;

public class Corporation {
	public static void main(String []args){
        Corporation cor1 = new Corporation();
        Corporation cor2 = new Corporation();
        Corporation cor3 = new Corporation();
        cor1.CalSalary(35, 7.50);
        cor2.CalSalary(41, 8.20);
        cor3.CalSalary(73, 10.00);
    }

    private void CalSalary(int WorkTime, double BaseSalary){
        if(BaseSalary < 8 || WorkTime > 60){
            System.out.println("Error");
            return ;
        }
        if(WorkTime <= 40){
            System.out.println("The All Salary is " + (WorkTime * BaseSalary));
        }else{
            System.out.println("The All Salary is " + (40 * BaseSalary + (WorkTime - 40) * (BaseSalary * 1.5)));
        }
    }
}
