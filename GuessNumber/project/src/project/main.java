package project;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum;
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
//		List<String> s = Collections.synchronizedList(new ArrayList<String>());
//		s.add("s");1892
//		s.add("b");
//		System.out.println("List = " + s);
		menu.info();
		sum = sc.nextInt();
		menu.menus(sum);	
		
		
		
	}
}

class Menu {
	
	String computerHistory = "";
	String humanityHistory = "";

	String test = "";
	public void info() {
		System.out.println();
		System.out.println();
		System.out.println("-------幾Ａ幾Ｂ遊戲-------");
		System.out.println("資訊二甲");
		System.out.println("40717011");
		System.out.println("熊少愆");
		System.out.println("----------選單----------");
		System.out.println("1.與電腦互猜");
		System.out.println("2.電腦猜你");
		System.out.println("3.猜電腦");
		System.out.print("輸入選擇：");
	}
	
	public void menus(int sum) {
		Computer comp = new Computer();
		Humanity human = new Humanity();
		
		int frequency;
		
		switch(sum) {
			case 1:
				frequency = 0;
				comp.prepare();
				human.prepare();
				while(true) {
					while(comp.isOver == 0 || human.isOver == 0) {
						System.out.println("---------第" + (frequency + 1) + "回合---------");
						
						if (frequency != 0) {
							System.out.println("玩家歷史紀錄  |  電腦歷史紀錄");
							System.out.println("------------+------------");
							System.out.printf("%s",test);
						}
						
						human.ask();
						human.check();
						test += human.inGuess + " (" + human.A + "A" + human.B +"B) | ";

						// 玩家獲勝終止條件
						if (human.isOver == 1) break;
						
						System.out.println("-----------------------");
						
						if (frequency != 0) {
							System.out.println("玩家歷史紀錄  |  電腦歷史紀錄");
							System.out.println("------------+------------");
							System.out.printf("%s",test);
						}
						System.out.println();
						
						
						comp.ask();
						comp.answer();
						comp.think();
						
						test += comp.history + " (" + comp.a + "A" + comp.b + "B)\n";
						// 電腦獲勝終止條件
						if (comp.isOver == 1) break;
						System.out.println();
						// 
						frequency++;
					}
					info();
					Scanner sc = new Scanner(System.in);
					sum = sc.nextInt();
					menus(sum);
				}
			case 2:
				frequency = 0;
				comp.prepare();
				while(comp.isOver == 0) {
					System.out.println("---------第" + (frequency + 1) + "回合---------");
					if (frequency != 0) {
						computerHistory += comp.history + "-";
						System.out.println("電腦歷史紀錄：" + computerHistory);
					}
					if (comp.isOver == 1) {
						break;
					}
					
					comp.ask();
					comp.answer();
					comp.think();
					frequency++;
				}
				info();
				Scanner sc = new Scanner(System.in);
				sum = sc.nextInt();
				menus(sum);
				break;
			case 3:
				frequency = 0;
				human.prepare();
				while(human.isOver == 0) {
					System.out.println("---------第" + (frequency + 1) + "回合---------");
					if (human.isOver == 1) {
						break;
					}
					if (frequency != 0) {
						humanityHistory += human.inGuess + "(" + human.A + "A" + human.B + "B)" + "-" ;
						System.out.println("玩家歷史紀錄：" + humanityHistory);
					}
					human.ask();
					human.check();
					frequency++;
				}
				info();
				Scanner scq = new Scanner(System.in);
				sum = scq.nextInt();
				menus(sum);
				
				break;
			default:
				break;
		}
	}
}
