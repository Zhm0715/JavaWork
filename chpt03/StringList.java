package chpt03;

import java.util.Scanner;

class University{
	String Name;
	int Fee;
	University(String s, int fee){
		Name = s;
		Fee = fee;
	}
	
	University(){}
	
	public String toString() {
		return "Name:" + this.Name + " Fee:" + this.Fee;
	}

}

public class StringList {
	University Info[];

	StringList(){
		Info = new University[5];           // 创建一个容纳五个指针的数组
	}
	
	public String ShowMaxLenName() {
		int pos = 0;
		for(int i = 0;i < 5;++i) {
			if(Info[pos].Name.length() < Info[i].Name.length()) {
				pos = i;
			}
		}
		return Info[pos].Name.toUpperCase();
	}
	
	public String ShowMaxExpen() {
		int pos = 0;
		for(int i = 0;i < 5;++i) {
			if(Info[pos].Fee < Info[i].Fee) {
				pos = i;
			}
		}
		return Info[pos].Name;
	}
	
	@SuppressWarnings("resource")
	public University Found() {
		System.out.println("请输入要查找的校名");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		for(int i = 0;i < 5;++i) {
			if(str.toUpperCase().equals(Info[i].Name.toUpperCase())) {   // == 引用 equals 值
				return Info[i];
			}
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringList sop = new StringList();
		for(int i = 0;i < 5;++i) {
			System.out.println("请输入大学名称:");
			String str = input.next();
			System.out.println("请输入学费:");
			int fee = input.nextInt();
			sop.Info[i] = new University(str, fee);     // 数组内元素创建
		}
		System.out.print("最长名称:");
		System.out.println(sop.ShowMaxExpen());
		System.out.println("最贵学费:");
		System.out.println(sop.ShowMaxLenName());
		University u = sop.Found();
//		System.out.println();
		if(u == null) {
			System.out.println("未找到");
		}else {
			System.out.println(u);
		}
	}

}
