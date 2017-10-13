package user;

public class UserVO {
	private String name;
	private String sex;
	private String birth;
	private String phone;
	private String address;
	private boolean active;

	// constructor
	public UserVO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {// getParameter로 하나씩 가져감
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// 오버라이딩
	public String toString() {
		return name + sex + birth + phone + address + active;
	}

}
