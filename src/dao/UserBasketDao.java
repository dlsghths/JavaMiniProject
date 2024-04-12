package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import dto.UserBasket;

public class UserBasketDao {
	// 장바구니에 물품을 담는 경우
	static UserBasketDao instance;
	public static UserBasketDao getInstance() {
		if(instance == null) {
			instance = new UserBasketDao();
		}
		return instance;
	}
	
	// 장바구니 리스트 추가, 삭제
	// 장바구니 리스트 물품 개수 변경
	// 장바구니 리스트 조회
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 장바구니 리스트 추가
	public boolean basketInsert(UserBasket userBasket) {
		db.connectDB();
		// 아이디, 상품 번호, 상품명, 가격, 재고량
		query = String.format("insert into UserBasket values ('%s', '%d', '%s', '%d', '%d', '%s');",
				userBasket.userId, userBasket.productNum, userBasket.productName, userBasket.productPrice, userBasket.productCount, userBasket.productImage);
		System.out.println("basketInsert: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.basketInsert : " + e.getMessage());
			return false;
		}
	}
	
	// 장바구니 리스트 물품 개수 수정
	public void basketUpdate(UserBasket userBasket, int count) {
		db.connectDB();
		// 아이디, 상품 번호, 상품명, 가격, 재고량
		query = String.format("update UserBasket set productCount = '%d' where userId = '%s' and productNum = '%d';",
				count, userBasket.userId, userBasket.productNum);
		System.out.println("basketUpdate: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.basketUpdate : " + e.getMessage());
		}
	}
	
	// 장바구니 리스트 삭제
	public void basketDelete(UserBasket userBasket) {
		db.connectDB();
		query = String.format("delete from UserBasket where userId = '%s' and productNum = '%d';",
				userBasket.userId, userBasket.productNum);
		System.out.println("basketDelete: " + query);
		try {
			db.stmt.executeUpdate(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.basketDelete : " + e.getMessage());
		}
	}
	
	// 장바구니 리스트 조회
	public ArrayList<UserBasket> basketSelect(String userId) {
		ArrayList<UserBasket> list = new ArrayList<UserBasket>();
		db.connectDB();
		query = String.format("select * from UserBasket where userId = '%s';", userId);
		System.out.println("basketSelect: " + query);
		
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				// 아이디, 상품 번호, 상품명, 가격, 재고량
				UserBasket userBasket = new UserBasket(result.getString("userId"), result.getInt("productNum"),
						result.getString("productName"), result.getInt("productPrice"), result.getInt("productCount"), result.getString("productImage"));
				list.add(userBasket);
			}
			db.disconnectDB();
			return list;
		} catch (Exception e) {
			System.out.println("에러.basketSelect : " + e.getMessage());
			return null;
		}
	}
}
