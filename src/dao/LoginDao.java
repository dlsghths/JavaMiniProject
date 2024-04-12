package dao;

import java.sql.ResultSet;

import dto.User;

public class LoginDao {
	// 로그인 요청
	// 로그아웃
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 로그인 요청
	public User requestLogin(String userId, String userPw) {
		db.connectDB();
		
		query = String.format("select * from User where userId = '%s' and userPw = '%s';", userId, userPw);
		System.out.println("requestLogin:" + query);
		User loginResult = new User();
		try {
			ResultSet result = db.stmt.executeQuery(query);
			if(result.next()) {
				loginResult.userId = result.getString("userId");
				loginResult.userPw = result.getString("userPw");
				loginResult.userName = result.getString("userName");
				loginResult.birthday = result.getInt("birthday");
				loginResult.address = result.getString("address");
				loginResult.phoneNumber = result.getString("phoneNumber");
				loginResult.isAdmin = result.getBoolean("isAdmin");
				
				db.disconnectDB();
				return loginResult;
			} else {
				db.disconnectDB();
				return null;
			}
		} catch (Exception e) {
			System.out.println("에러.requestLogin : " + e.getMessage());
			return null;
		}
	}
	
	// 로그아웃 요청
//	public boolean requestLogOut(String userId) {
//		
//	}
}
