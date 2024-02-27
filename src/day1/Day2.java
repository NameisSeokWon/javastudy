package day1;

//import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) {
		String result = method1();		
		System.out.println(result);
		String result1 = method2("hello");
		System.out.println(result1);
		method3();
		String result2 = method4("철수", "영희");
		System.out.println(result2);		
		int methodSum = sum(1,100);
		System.out.println(methodSum);
		
	}	
	public static String method1() {
		return "안녕하세요";		
	}
	public static String method2(String word) {
		return word + word;
	}
	public static void method3() {
		System.out.println("정해진 일을 하는 메소드");
	}
	public static String method4(String str1, String str2) {
		return str1 + str2;
	}
	public static int sum(int sNum, int eNum) {
		int sum = 0;
		for(int i = sNum; i<= eNum; i ++) {
			if(i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}

// 전달된 파라메터가 홀수인지 짝수인지 판단하는 메소드
// 리턴값 X
// 파라메터 int
// 결과 예시 ) 5는 홀수 입니다. << syso
