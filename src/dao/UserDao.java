package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.User;

public class UserDao {
	static UserDao instance;
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	// 사용자 정보 추가, 변경, 삭제, 조회
	// 사용자 아이디 조회, 비밀번호 조회
	// 사용자 아이디 중복 조회
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 사용자 정보 추가
	public boolean userInsert(User user) {
		db.connectDB();
		// 아이디, 비밀번호, 이름, 생년월일, 주소, 휴대폰 번호
		query = String.format("insert into User values ('%s', '%s', '%s', '%d', '%s', '%s', '%d');",
				user.userId, user.userPw, user.userName, user.birthday, user.address, user.phoneNumber, 0);
		System.out.println("userInsert: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.userInsert : " + e.getMessage());
			return false;
		}
	}
	
	// 사용자 정보 수정
	public boolean userUpate(User userBefore, User user) {
		db.connectDB();
		// 아이디, 비밀번호, 이름, 생년월일, 주소, 휴대폰 번호
		query = String.format("update User set userId = '%s', userPw = '%s', "
				+ "userName = '%s', birthday = '%d', address = '%s', phoneNumber = '%s' where userId = '%s';",
				user.userId, user.userPw, user.userName, user.birthday, user.address, user.phoneNumber, userBefore.userId);
		System.out.println("userUpate: " + query);
		try {
			db.stmt.executeUpdate(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.userUpate : " + e.getMessage());
			return false;
		}
	}
	
	// 사용자 정보 삭제
	public void userDelete(User user) {
		db.connectDB();
		query = String.format("delete from User where userId = '%s';", user.userId);
		System.out.println("userDelete: " + query);
		try {
			db.stmt.executeUpdate(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.userDelete : " + e.getMessage());
		}
	}
	
	// 사용자 정보 조회
	public ArrayList<User> userSelect(String userId, String userName) {
		// 1. 사용자 아이디로 조회
		// 2. 사용자 이름으로 조회
		// 3. 사용자 전체 조회
		System.out.println("aaaaaaaaaaaaaaaaaa");
		ArrayList<User> list = new ArrayList<User>(); // 사용자 정보를 담을 list 생성
		db.connectDB();
		if(userId != null) {
			query = String.format("select * from User where userId = '%s';", userId);
		} else if(userName != null) {
			query = String.format("select * from User where userName = '%s';", userName);
		} else {
			query = String.format("select * from User;");
		}
		System.out.println("userSelect: " + query);
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				// 아이디, 이름, 생년월일, 주소, 휴대폰 번호
				User user = new User(result.getString("userId"), result.getString("userName"),
						result.getInt("birthday"), result.getString("address"), result.getString("phoneNumber"));
				list.add(user);
			}
			db.disconnectDB();
			return list;
		} catch (Exception e) {
			System.out.println("에러.userSelect : " + e.getMessage());
			return null;
		}
	}
	
	// 사용자 아이디 찾기
	public String searchUserId(String userName, String phoneNumber) {
		db.connectDB();
		query = String.format("select userId from User where userName = '%s' and phoneNumber = '%s';", userName, phoneNumber);
		System.out.println("searchUserId: " + query);
		try {
			ResultSet result = db.stmt.executeQuery(query);
			String userId = "";
			if(result.next()) {
				userId = result.getString("userId");	
			}
			db.disconnectDB();
			return userId;
		} catch (Exception e) {
			System.out.println("에러.searchUserId : " + e.getMessage());
			return null;
		}
	}
	
	// 사용자 비밀번호 찾기
	public String searchUserPw(String userId, String userName, String phoneNumber) {
		// TODO 비밀번호 찾기에 성공하면 새로 사용할 비밀번호 변경을 요청
		db.connectDB();
		query = String.format("select userPw from User where userId = '%s' and "
				+ "userName = '%s' and phoneNumber = '%s';", userId, userName, phoneNumber);
		System.out.println("searchUserPw: " + query);
		try {
			ResultSet result = db.stmt.executeQuery(query);
			String userPw = "";
			if(result.next()) {
				userPw = result.getString("userPw");
			}
			db.disconnectDB();
			return userPw;
		} catch (Exception e) {
			System.out.println("에러.searchUserPw : " + e.getMessage());
			return null;
		}
	}
	
	// 사용자 아이디 중복 확인
	public boolean checkDuplication(String userId) {
		db.connectDB();
		query = String.format("select * from User where userId = '%s';", userId);
		System.out.println("checkDuplication: " + query);
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				if(result.getString("userId") != null) {
					// 사용자가 입력한 아이디가 이미 존재할 경우
					return false;
				}	
			}
			db.disconnectDB();
			return true;	
		} catch (Exception e) {
			System.out.println("에러.checkDuplication : " + e.getMessage());
			return false;
		}
	}
}
