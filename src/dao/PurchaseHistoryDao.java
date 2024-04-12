package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PurchaseHistory;
import dto.UserBasket;

public class PurchaseHistoryDao {
	// 장바구니에 넣은 상품을 구매한 경우
	static PurchaseHistoryDao instance;
	public static PurchaseHistoryDao getInstance() {
		if(instance == null) {
			instance = new PurchaseHistoryDao();
		}
		return instance;
	}
	
	// 상품 구입
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 상품 구입
	public boolean purchaseInsert(ArrayList<PurchaseHistory> purchaseList) {
		db.connectDB();
		// 아이디, 상품 번호, 상품명, 가격, 재고량
		query = String.format("insert into PurchaseHistory values ");
		query += String.format("('%s', '%d', '%s', '%d', '%d', '%s')",
				purchaseList.get(0).userId, purchaseList.get(0).productNum, purchaseList.get(0).productName,
				purchaseList.get(0).productPrice, purchaseList.get(0).productCount, purchaseList.get(0).productImage);
		if(purchaseList.size() >= 2) {
			for(int i = 1; i < purchaseList.size(); i++) {
				query += String.format(", ('%s', '%d', '%s', '%d', '%d', '%s')",
						purchaseList.get(i).userId, purchaseList.get(i).productNum, purchaseList.get(i).productName,
						purchaseList.get(i).productPrice, purchaseList.get(i).productCount, purchaseList.get(i).productImage);
			}
		}
		query += ";";
		System.out.println("purchaseInsert: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.purchaseInsert : " + e.getMessage());
			return false;
		}
	}
	
	// 상품 구매 내역 조회
	public ArrayList<PurchaseHistory> purchaseSelect(String userId) {
		ArrayList<PurchaseHistory> list = new ArrayList<PurchaseHistory>();
		db.connectDB();
		query = String.format("select * from PurchaseHistory;");
		if(userId != null) {
			query = String.format("select * from PurchaseHistory where userId = '%s';", userId);			
		}
		System.out.println("purchaseSelect: " + query);
		
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				// 아이디, 상품 번호, 상품명, 가격, 재고량
				PurchaseHistory purchaseHistory = new PurchaseHistory(result.getString("userId"), result.getInt("productNum"), result.getString("productName"),
						result.getInt("productPrice"), result.getInt("productCount"), result.getString("productImage"));
				list.add(purchaseHistory);
			}
			db.disconnectDB();
			return list;
		} catch (Exception e) {
			System.out.println("에러.purchaseSelect : " + e.getMessage());
			return null;
		}
	}
}
