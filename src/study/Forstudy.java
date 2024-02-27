package study;

import java.util.Scanner;

public class Forstudy {

	public static void main(String[] args) {
		foo();		
	}
	static void foo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력하세요 : ");
		int input = sc.nextInt();
		long sum = 1;
		for(int i = input; i>0; i--) {
			sum *= i;
		}
		System.out.println(sum);
	}
}

	


/*팩토리얼 프로그램*/