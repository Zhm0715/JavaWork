package chpt02;

public class UntilOne {

	public static void main(String[] args) {
       int cnt = 1;
       int num1;
       int num2;
       while(true){
            num1 = (int)(Math.random() * 7);
            num2 = (int)(Math.random() * 7);
            if(num1 == 1 && num2 == 1){
                System.out.println("Snake eyes. Count = " + cnt);
                break;
            }else{
                cnt = cnt + 1;
            }
       }
	}

}
