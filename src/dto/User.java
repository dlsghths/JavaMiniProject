package dto;

public class User {
	// 회원정보 관리
	public String userId; // 사용자 아이디
	public String userPw; // 사용자 비밀번호
	public String userName; // 사용자 이름
	public int birthday; // 사용자 생년월일
	public String address; // 사용자 주소
	public String phoneNumber; // 사용자 전화번호
	public boolean isAdmin; // 사용자와 관리자 구분

	public User() {}
	
	// 사용자 회원가입 시 사용
	public User(String userId, String userPw, String userName, int birthday,
			String address, String phoneNumber) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.birthday = birthday;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	// 사용자 정보 조회시 사용
	public User(String userId, String userName, int birthday, String address, String phoneNumber) {
		this.userId = userId;
		this.userName = userName;
		this.birthday = birthday;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
