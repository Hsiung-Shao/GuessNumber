package project;

import java.util.Arrays;
import java.util.Scanner;

public class Humanity {
	int right,isOver;
	String computerGuess = "";
	String inGuess = "";
	public int A;
	public int B;
	
	public void prepare() {
		isOver = 0;
		int[] randomnumber = new int[4];
	    int[] temp = new int[10];          
	    int index = 0;

	    randomnumber[3] = 11;               
	    while (randomnumber[3] == 11) {
	    	int select = (int) (Math.random() * 10); 
	        if (temp[select] == 0) {
	        	randomnumber[index] = select;
	            temp[select] = 1;
	            index = index + 1;
	        }
	    }
	    for(int i = 0; i < 4;i++) {
	        computerGuess += randomnumber[i];
	    }
	        
	        
	    
	}
	public void ask() {
		int unit1,unit10,unit100,unit1000;
		System.out.print("玩家問～ 請問是:");
		inGuess = "";
		do { // get A,b value
			Scanner sc = new Scanner(System.in);
			inGuess = sc.next();
			if (inGuess.length() == 4) {
				unit1 = (Integer.valueOf(inGuess) / 1) % 10;
				unit10 = (Integer.valueOf(inGuess) / 10) % 10;
				unit100 = (Integer.valueOf(inGuess) / 100) % 10;
				unit1000 = (Integer.valueOf(inGuess) / 1000) % 10;
				
				if (unit1 != unit10 && unit1 != unit100 && unit1 != unit1000 && unit10 != unit100 && unit10 != unit1000 && unit100 != unit1000 && unit1000 != 0) {
					right = 1;
				}else {
					System.out.println("輸入錯誤！");
					ask();
				}
			}else {
				System.out.print("輸入錯誤！");
			}
		}while(right != 1);
	}
	
	public void check() {
		boolean check[]=new boolean[4]; //紀錄每個位數是否檢查過
		Arrays.fill(check, false);
		A = 0;
		B = 0;
		//檢查有幾A
		for(int i=0;i<4;i++){
			if(computerGuess.charAt(i) == inGuess.charAt(i)){
				A++;
				check[i]=true;
			}
		}
		//檢查有幾B
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(!check[j] && computerGuess.charAt(j) == inGuess.charAt(i)){
					B++;
					check[j]=true;
					break;
				}
			}
		}
		
			
		//Output
		if(A == 4){
			System.out.println("恭喜你答對了");
			isOver = 1;
		}
		else{
			System.out.println(A+"A"+B+"B");
		}
	}
	
}
