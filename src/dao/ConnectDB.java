package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	String sqlUrl = "jdbc:mysql://localhost:3306/shoppingmall?serverTimezone=UTC";
	String hostName = "root";
	String password = "1234";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet result = null;
	
	static ConnectDB instance;
	public static ConnectDB getInstance() {
		if(instance == null) {
			instance = new ConnectDB();
		}
		return instance;
	}
	
	// 데이터베이스 연결
	public void connectDB() {
		try {
			conn = DriverManager.getConnection(sqlUrl, hostName, password);
			stmt = conn.createStatement();
			System.out.println("DB 연결 시작");
		} catch (Exception e) {
			System.out.println("에러.connectDB : " + e.getMessage());
		}
	}
	
	// 데이터베이스 연결 종료
	public void disconnectDB() {
		try {
			stmt.close();
			conn.close();
			System.out.println("DB 연결 종료");
		} catch (Exception e) {
			System.out.println("에러.disconnectDB : " + e.getMessage());
		}
	}
}
