package day1;

public class CarInfo {
	
	private String name;
	private long price;
	private String color;
	private UserInfo ownerInfo; // 정보를 가지고 있어야 해서


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public UserInfo getOwnerInfo() {
		return ownerInfo;
	}
	public void setOwnerInfo(UserInfo ownerInfo) {
		this.ownerInfo = ownerInfo;
	}
}
