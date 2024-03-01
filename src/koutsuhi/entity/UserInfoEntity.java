package koutsuhi.entity;

public class UserInfoEntity {
	
	//회원가입 유저정보 저장을 위한 객체
	//유저 ID
	//유저 PW
	//유저 PW2(확인)
	//유저 성별
	private String id;
	private String pw;
	private String name;
	private int gender;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}	
	@Override
	public String toString() {
		return "UserInfoEntity [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + "]";
	}

}
