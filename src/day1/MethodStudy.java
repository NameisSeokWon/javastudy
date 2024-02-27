package day1;

import java.util.ArrayList;
import java.util.List;

public class MethodStudy {

	public static void main(String[] args) {
		
		MethodStudy ms = new MethodStudy();
		ms.makeUserInfo();

	}
	// 가상의 유저 정보를 생성하는 메소드
	List<UserInfo> makeUserInfo() {
		
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		
		//String name = "김정화";
		UserInfo userInfo = new UserInfo(); // ()생성자  클래스 초기화
		userInfo.name = "김정화";
		userInfo.age = 45;
		userInfo.gender = "male";
		userInfo.isMarried = true;
//		userInfo.carList = "김정화";
		// List도 객체화해서 사용한다.
		List<String> carList = new ArrayList<String>();
		carList.add("프리우스");
		carList.add("NBox");		
		userInfo.carList = carList;		
		userInfoList.add(userInfo);
//////////////////////////////////////////////////////////////		
		userInfo = new UserInfo();
		userInfo.name = "이화정";
		userInfo.age = 42;
		userInfo.gender = "female";
		userInfo.isMarried = false;
		carList = new ArrayList<String>();
		carList.add("crown");
		userInfoList.add(userInfo);
		
		return userInfoList;	
	}
	ClassInfo makeClassInfo() {	
		
		ClassInfo classInfo = new ClassInfo();
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		
		MethodStudy ms = new MethodStudy();
		userInfoList = ms.makeUserInfo();
		classInfo.name = "강석원";
		classInfo.age = 18;
		classInfo.gender = "male";
		classInfo.parentsInfo = userInfoList;
	
		
		return classInfo;	
		
	}
	
	// 학급정보(ClassInfo)를 2개 생성해서 반환하는 메소드를 작성한다.
	// 학급정보는 학생이름, 학생나이, 성별, 부모님정보(List<UserInfo>)가 담겨있다.
	// 부모님정보는 아버지, 어머님 정보를 둘 다 담고 있다.
	
	// 1대의 자동차 정보(CarInfo)를 반환하는 메소드를 작성
	// 차이름, 차가격, 차색상, 고객정보(ownerinfo)
	// name, price, color, ownerInfo
	// makeCarInfo
	CarInfo makeCarInfo() {			
		
		CarInfo carInfo = new CarInfo();
		//List<UserInfo> userInfoList = new ArrayList <UserInfo>();
		//MethodStudy ms = new MethodStudy();
		//userInfoList = ms.makeUserInfo();
		carInfo.name = "benz";
		carInfo.price = 2500000000L;
		carInfo.color = "gray";
		carInfo.ownerInfo = userInfoList;
		
		return carInfo;	
		
	}
}
