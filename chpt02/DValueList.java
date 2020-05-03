package chpt02;

import java.util.Scanner;

public class DValueList {

	public static void main(String[] args) {
		System.out.println("请输入数的个数及差值:");
        Scanner input = new Scanner(System.in);
        int Cnt = input.nextInt();
        int Dvalue = input.nextInt();
        int []Value = new int[Cnt];
        System.out.print("请输入" + Cnt + "个整数:");
        for(int i = 0;i < Cnt;++i){
            Value[i] = input.nextInt();
        }
        boolean flag = true;
        for(int i = 1;i < Cnt;++i){
            if(Math.abs(Value[i - 1] - Value[i]) > Dvalue){
                System.out.println("数据从第" + i + "个数开始截断");
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.print("这是" + Cnt + "个数组成的连续序列,差值为" + Dvalue);
        }
        input.close();
	}

}
