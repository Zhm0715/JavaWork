package chpt03;

import java.util.Scanner;

public class Slot {
	String slotNumber;
	boolean reserved;    // 是否被预定
	BookingPerson person;
	
	public String toString() {
		return this.slotNumber;
	}
 
	public Slot(String slotNum){
		this.slotNumber = new String(slotNum);
	} 
	// Marks the slot as reserved 
	public void Reserved() { 
		this.reserved = true;
	} 
	
	public boolean Reserved(BookingPerson P) {
		if(this.isReserved()) {
			return false;
		}
		this.person = P;
		this.Reserved();
		return true;
	}
	
	// Marks the slot as available 
	public void unReserved() { 
		this.reserved = false;
		this.person = null;
	} 
	// Returns true if the slot is reserved, false otherwise 
	public boolean isReserved() { 
		return this.reserved;
	} 
	// Returns the slot number as available 
	public String getSlotNum() { 
		return this.slotNumber;
	}

	public static void main(String[] args){ 
	// Small test of the Slot class 
		Slot example = new Slot("North Building 2-305-22");
		System.out.println("Slot number(should be North Building 2-305-22): " + example.getSlotNum());
		System.out.println("Reserved? (should be false): " + example.isReserved());
		example.Reserved();
		System.out.println("Reserved? (should be true): " + example.isReserved());
		example.unReserved();
		System.out.println("Reserved? (should be false): " + example.isReserved());
		} 
}

class BookingPerson{
	private String Name;
	private int Time;
	
	BookingPerson(){}
	
	BookingPerson(String name, int time){
		this.Name = name;
		this.Time = time;
	}
	
	public int getTime() {
		return this.Time;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public double getFee(double Fee) {
		return this.Time * Fee;
	}
}

// Building.java
class Building{ 
	private static int openTime;
	private static int CloseTime;
	private static final int MaxSize = 100;    // 最多座位数
	private static double Fee = 4.5;     // 每小时费用
	private static double OvertimeFee = 6;  // 超时每小时费用
	String BuildName;
	Slot []slotList;
	int nowslotNum;
	
	public static double getFee() {
		return Building.Fee;
	}
	
	Building(String str){
		this.openTime = 9;
		this.CloseTime = 9;
		this.slotList = new Slot[MaxSize];
		this.BuildName = new String(str);
		this.nowslotNum = 0;
	}
	public String toString() {
		return "Address: " + this.BuildName + 
			   "\nBuildings are open daily from "+ getOpenTime() + "am to " + getCloseTime() +"pm.\nAllSlot:" 
				+ this.nowslotNum + "\nFee:" + this.Fee + "\nOvertimeFee:" + this.OvertimeFee;
	}
	
	public void ModifyTime(int open, int close) {
		this.openTime = open;
		this.CloseTime = close;
	}
	
	public static int getOpenTime() {
		return openTime;
	}
	
	public static int getCloseTime() {
		return CloseTime;
	}
	
	public void ModifyFee(int fee) {
		this.Fee = fee;
	}
	
	public void addSlot(Slot s) {
		this.slotList[this.nowslotNum] = s;
		++this.nowslotNum;
	}
	
	public static void printOpeningHours() {
		System.out.println("Buildings are open daily from "+ getOpenTime() + "am to " + getCloseTime() +"pm.");
	}
	
	public void printAddress() {
		System.out.println(this.BuildName);
	}
	
	public void bookSlot(String slotNum) {
		for(int i = 0;i < this.nowslotNum;++i) {
			if(this.slotList[i].getSlotNum().equals(slotNum)) {
				if(this.slotList[i].isReserved()) {
					System.out.println("Sorry, this slot is already booked.");
					return ;
				}
				this.slotList[i].Reserved();
				System.out.println("You successfully booked The" + slotNum);
				return ;
			}
		}
		System.out.println("Sorry, this slot is not in our building.");
	}
	
	public void printAvailableSlots() {
		boolean flag = false;
		for(int i = 0;i < this.nowslotNum;++i) {
			if(!this.slotList[i].isReserved()) {
				flag = true;
				System.out.println(this.slotList[i].getSlotNum());
			}
		}
		if(!flag) {
			System.out.println("No Slot in our building");
		}
	}
	
	public void returnSlot(String s) {
		for(int i = 0;i < this.nowslotNum;++i) {
			if(s.contains(this.slotList[i].getSlotNum())){
				if(this.slotList[i].isReserved()) {
					this.slotList[i].unReserved();
					System.out.println("You successfully returned The " + this.slotList[i].getSlotNum());
					return ;
				}else {
					System.out.println("This Slot is Unreserved");
				}
			}
		}
		System.out.println("Sorry, this slot is not in our building.");
	}
	
	public static void main(String[] args){ 
		Building firstBuiling = new Building("North Library St. "); 
		Building secondBuilding = new Building ("South main St."); 
		// Add four slots to the first library 
		firstBuiling.addSlot(new Slot("North Building 2-305-22"));
		firstBuiling.addSlot(new Slot("North Building 1-201-11"));
		firstBuiling.addSlot(new Slot("North Building 2-306-24"));
		firstBuiling. addSlot (new Slot("North Building 1-103-15"));
		
		// Print opening hours and the addresses 
		System.out.println("Building hours:");
		printOpeningHours();
		System.out.println();
		System.out.println("Building addresses:");
		firstBuiling.printAddress();
		secondBuilding.printAddress();
		System.out.println();
		
		// Try to book the North Building 2-305-22 
		System.out.println("Booking The North Building 2-305-22:");
		firstBuiling.bookSlot("North Building 2-305-22");
		firstBuiling. bookSlot ("North Building 2-305-22");
		secondBuilding. bookSlot ("North Building 2-305-22");
		System.out.println();
		
		// Print the names of all available slots from both buildings 
		System.out.println("Slots available in the first building:");
		firstBuiling.printAvailableSlots();
		System.out.println();
		System.out.println("Slots available in the second building:");
		secondBuilding.printAvailableSlots();
		System.out.println();
		
		// Return North Building 2-305-22s to the first building
		System.out.println("Returning the North Building 2-305-22:"); 
		firstBuiling.returnSlot("North Building 2-305-22Rings"); 
		System.out.println(); 

// Print the name of all available slots from the first building 
		System.out.println("Slots available in the first building:"); firstBuiling.printAvailableSlots();
		
	}
}
/*
	程序输出结果如下：
	Building  hours:
Buildings are open daily from 9am to 9pm.

Building addresses:
North Building 1-201-11.
South Main St.

Booking the North Building 2-305-22:
You successfully booked The North Building 2-305-22
Sorry, this slot is already booked.
Sorry, this slot is not in our building.

Slots available in the first building:
North Building 1-201-11
North Building 2-306-24
North Building 1-103-15

Slots available in the second Building:
No Slot in our building

Returning the North Building 1-201-11
You successfully returned The North Building 1-201-11

Slots available in the first building:
North Building 2-305-22
North Building 1-201-11
North Building 2-306-24
North Building 1-103-15
*/
