package chpt03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookSystem {
	private Building []BuildList;
	private static final int MaxBuild = 10;
	private int NowBuild = 0;
	
	BookSystem(){
		BuildList = new Building[MaxBuild];
	}
	
	public void AddBuild(Building b) {
		if(NowBuild >= MaxBuild) {
			System.out.println("楼数量已达到上限 无法添加");
			return ;
		}
		BuildList[NowBuild++] = b;
	}
	
	public void ShowAllBuildInfo() {
		for(int i = 0;i < this.NowBuild;++i) {
			System.out.println(this.BuildList[i]);
		}
	}
	
	public void ModifyFee(Building b, int fee) {
		b.ModifyFee(fee);
	}
	
	public void AdminSytem() {
		System.out.println("请输入要执行的操作");
		System.out.println("1 显示所有建筑信息");
		System.out.println("2 修改费用");
		System.out.println("3 修改开放时间");
		int choice;
		Scanner input = new Scanner(System.in);
		try {
			choice = input.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("输入错误 请重新输入");
			return ;
		}
		switch(choice) {
		case 1:
			this.ShowAllBuildInfo();
			break;
		case 2:
			System.out.println();
			int fee = 0;
			
			while(true) {
				try {
					System.out.println("请输入修改后的每小时费用:");    // 可扩展为修改单独一个建筑费用 故费用非static(因为时间关系 未完成)
					fee = input.nextInt();
					for(int i = 0;i < this.NowBuild;++i) {
						this.BuildList[i].ModifyFee(fee);
					}
					break;
				}catch(InputMismatchException e) {
					System.out.println("输入错误 请重新输入");
				}
			}
			
			break;
		case 3:
			int opentime = 0;
			int closetime = 0;
			
			while(true) {
				try {
					System.out.println("请输入开放时间:");
					opentime = input.nextInt();
					System.out.println("请输入关闭时间:");
					closetime = input.nextInt();
					this.BuildList[0].ModifyTime(opentime, closetime);
					break;
				}catch(InputMismatchException e) {
					System.out.println("输入错误 请重新输入");
				}
			}

			break;
			default:
				System.out.println("无此选项 请重新输入");
		}
	}
	
	public void BookingSystem() {
		System.out.println("请输入选项");
		System.out.println("1 预订座位");
		System.out.println("2 退订座位");
		System.out.println("3 查询费用");
		int choice = 0;
		int buildpos = 0;
		int slotpos = 0;
		Scanner in = new Scanner(System.in);
		try {
			choice = in.nextInt();
			switch(choice) {
			case 1:
				for(int i = 0;i < this.NowBuild;++i) {
					System.out.println((i + 1) + ":" + this.BuildList[i]);
				}
				System.out.println("请输入要选择的建筑号:");
				try {
					buildpos = in.nextInt();
					if(buildpos > this.NowBuild) {
						System.out.println("无此建筑");
						return ;
					}
					for(int j = 0;j < this.BuildList[buildpos - 1].nowslotNum;++j) {
						System.out.println((j + 1) + " : " + this.BuildList[buildpos - 1].slotList[j]);
					}
					System.out.println("请输入要选择的座位号:");
					slotpos = in.nextInt();
					System.out.println("预订成功");
					System.out.println("请输入您的姓名  以便退订");
					String pName = in.next();
					System.out.println("请输入预订时间");
						
					while(true) {
						try {
							int reserTime = in.nextInt();
							BookingPerson p = new BookingPerson(pName, reserTime);
							boolean isreser= this.BuildList[buildpos - 1].slotList[slotpos - 1].Reserved(p);
							break;
						}catch(InputMismatchException e) {
							System.out.println("输入错误 请输入数字");
						}
					}
						
				}catch(InputMismatchException e) {
					System.out.println("输入错误 请重新输入");
				}catch(Exception e) {
					System.out.println("发生错误 请再试一次");
				}
				break;
			case 2:
				System.out.println("请输入您的姓名");
				String pName = in.next();
				for(int i = 0;i < this.NowBuild;++i) {
					for(int j = 0;j < this.BuildList[i].nowslotNum;++j) {
						if(this.BuildList[i].slotList[j].isReserved()) {
							if(this.BuildList[i].slotList[j].person.getName().equals(pName)) {
								System.out.println(this.BuildList[i].slotList[j] + ":");
								System.out.println("The Booking Name:" + pName);
								System.out.println("The Booking Time:" + this.BuildList[i].slotList[j].person.getTime());
								System.out.println("这个座位是你的吗?(y/n)");
								String choice1 = in.next();
								if(choice1.charAt(0) == 'y') {
									this.BuildList[i].slotList[j].unReserved();
									System.out.println("退订成功");
									return ;
								}
							}
						}
					}
				}
				System.out.println("未找到您的座位");
				break;
			case 3:
				System.out.println("请输入您的预订时长(整数):");
				try {
					int BTime = in.nextInt();
					System.out.println("费用为:" + BTime * Building.getFee());
				}catch(Exception e) {
					System.out.println("输入有误 请重新输入");
				}
				break;
			default:
				System.out.println("无此选项 请重新输入");
			}
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("输入错误 请重新输入");
		}
	}
	
	public static void main(String[] args) {
		BookSystem system = new BookSystem();
		Scanner in = new Scanner(System.in);
		int choice = 0;
		Building firstBuiling = new Building("North Library St. "); 
		Building secondBuilding = new Building ("South main St."); 
		system.AddBuild(firstBuiling);
		system.AddBuild(secondBuilding);
		firstBuiling.addSlot(new Slot("North Building 2-305-22"));
		firstBuiling.addSlot(new Slot("North Building 1-201-11"));
		firstBuiling.addSlot(new Slot("North Building 2-306-24"));
		firstBuiling. addSlot (new Slot("North Building 1-103-15"));
		while(true) {
			System.out.println("请输入选项");
			System.out.println("1 座位预订");
			System.out.println("2 管理员登录");
			try {
				choice = in.nextInt();
				switch(choice) {
				case 1:
					system.BookingSystem();
					break;
				case 2:
					system.AdminSytem();
					break;
				default:
					System.out.println("无此选项 请重新输入");
				}
			}catch(InputMismatchException e) {
				System.out.println("输入错误 请重新输入");
			}
		}
	}

}
