package project;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Computer {
	int[][] answerBase = new int[5041][4];
	int isOver, restart;
	int remain;
	int a,b;
	int random;
	String history;
	
	public void prepare() {
		int i,tmp,unit1,unit10,unit100,unit1000;
		remain = 5040;
		tmp = 0;
		isOver = 0;
		for(i = 0; i < 10000; i++) { // 產生答案資料
			// 將產生的答案拆成四位數分別存入
			unit1 = (i / 1) % 10;
			unit10 = (i / 10) % 10;
			unit100 = (i / 100) % 10;
			unit1000 = (i / 1000) % 10;
			
			if (unit1 != unit10 && unit1 != unit100 && unit1 != unit1000 && unit10 != unit100 && unit10 != unit1000 && unit100 != unit1000) {
				answerBase[tmp][0] = unit1000;
				answerBase[tmp][1] = unit100;
				answerBase[tmp][2] = unit10;
				answerBase[tmp][3] = unit1;
				tmp++;
			}
		}
	}
	
	public void ask() {
		int i;
		history = "";
		System.out.print("電腦問～ 請問是:");
		long time = System.currentTimeMillis();
		SecureRandom rand = new SecureRandom();
		rand.setSeed(time/1000);
		while(true) {
			random = 0;
			for(i = 0; i < 4; i++) {
				random *= 10;
				random += rand.nextInt(10000)%10;
			}
			if (random < remain) break;
		}
		for(i = 0; i < 4; i++) {
			System.out.printf("%d",answerBase[random][i]);
			history +=  answerBase[random][i];
		}
		System.out.println("?");
	}
	
	public void answer() {
		int right = 0;
		do { // get A,b value
			do { // get A value
				System.out.print("xAxB:");
				Scanner scanner = new Scanner(System.in);
				String str = "";
				String[] input = null;
				str = scanner.next();
				input = str.split("A|B");
				a = Integer.valueOf(input[0]);
				b = Integer.valueOf(input[1]);
				if (a <= 4 && a >= 0 && b <= 4 && b >= 0) {
					right = 1;
				}else {
					System.out.print("輸入錯誤\n\n");
				}
			}while(right != 1);

			
			if (a + b > 4) {
				System.out.print("輸入錯誤\n\n");
				right = 0;
			}
		}while(right != 1);
		
		if (a == 4) {
			System.out.print("電腦：答對囉！！～～");
			isOver = 1;
		}else if (remain == 1) {
			System.out.print("電腦：你作弊！！");
			isOver = 1;
		}
	}
	
	
	public void think() {
		int i,j,k,aa,bb;
		int[] refer = new int[4];
		
		for (i = 0; i < 4; i++) {
			refer[i] = answerBase[random][i];
		}
		
		for (i = 0; i < remain; i++) {
			aa = bb = 0; // 放置A值和Ｂ值
			for (j = 0; j < 4; j++) { // 比對ｉ的答案和亂數的答案是幾Ａ幾Ｂ
				for (k = 0; k < 4; k++) {
					if (answerBase[i][j] == refer[k]) {
						if (j == k) {
							aa++;
						}else {
							bb++;
						}
					}
				}
			}
			if ( !((aa == a) && (bb == b)) ) { //  判斷Ａ和Ｂ值是否相等，相等的話將答案保存
				for (j = i; j < remain; j++) {
					for (k = 0; k < 4; k++) {
						answerBase[j][k] = answerBase[j+1][k];
					}
				}
				remain--;
				i--;
			}
		}
		if (remain == 0) {
			System.out.print("電腦：你作弊！！！");
			isOver = 1;
		}
	}
}
