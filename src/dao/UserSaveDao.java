package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import dto.UserSave;

public class UserSaveDao {
	// 찜하기에 물품을 담는 경우
	static UserSaveDao instance;
	public static UserSaveDao getInstance() {
		if(instance == null) {
			instance = new UserSaveDao();
		}
		return instance;
	}
	
	// 찜하기 리스트에 추가, 삭제
	// 찜하기 리스트 조회
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 찜하기 리스트 추가
	public boolean saveInsert(UserSave userSave) {
		db.connectDB();
		// 아이디, 상품 번호, 상품명, 가격
		query = String.format("insert into UserSave values ('%s', '%d', '%s', '%d', '%s');",
				userSave.userId, userSave.productNum, userSave.productName, userSave.productPrice, userSave.productImage);
		System.out.println("UserSave: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.UserSave : " + e.getMessage());
			return false;
		}
	}
	
	// 찜하기 리스트 삭제
	public void saveDelete(UserSave userSave) {
		db.connectDB();
		query = String.format("delete from UserSave where userId = '%s' and productNum = '%d';",
				userSave.userId, userSave.productNum);
		System.out.println("saveDelete: " + query);
		try {
			db.stmt.executeUpdate(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.saveDelete : " + e.getMessage());
		}
	}
	
	// 찜하기 리스트 조회
	public ArrayList<UserSave> saveSelect(String userId) {
		ArrayList<UserSave> list = new ArrayList<UserSave>();
		db.connectDB();
		query = String.format("select * from UserSave where userId = '%s';", userId);
		System.out.println("saveSelect: " + query);
		
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				// 아이디, 상품 번호, 상품명, 가격
				UserSave userSave = new UserSave(result.getString("userId"), result.getInt("productNum"),
						result.getString("productName"), result.getInt("productPrice"), result.getString("productImage"));
				list.add(userSave);
			}
			db.disconnectDB();
			return list;
		} catch (Exception e) {
			System.out.println("에러.saveSelect : " + e.getMessage());
			return null;
		}
	}
}
