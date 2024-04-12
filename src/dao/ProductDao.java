package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Product;

public class ProductDao {
	/*
	 * 상품 이미지의 경우
	 * 1. pc 내부 경로에 이미지를 저장하고 경로를 호출하는 방식
	 * 2. 실제 데이터베이스에 바이트 데이터로 저장하여 호출하는 방식
	 * 
	 * 1번 방식으로 사용하여 DB에 파일 경로를 저장
	 */
	static ProductDao instance;
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	// 상품 정보 추가, 수정, 삭제, 조회
	String query = "";
	ConnectDB db = ConnectDB.getInstance();
	
	// 상품 정보 추가
	public boolean productInsert(Product product) {
		db.connectDB();
		// 상품 번호, 상품명, 상품 설명, 가격, 재고량, 이미지 경로
		query = String.format("insert into Product values ('%d', '%s', '%s', '%d', '%d', '%s');",
				product.productNum, product.productName, product.productExplanation, product.productPrice, product.productInventory, product.productImage);
		System.out.println("productInsert: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
			return true;
		} catch (Exception e) {
			System.out.println("에러.productInsert : " + e.getMessage());
			return false;
		}
	}
	
	// 상품 정보 수정
	public void productUpdate(Product productBefore, Product product) {
		db.connectDB();
		// 상품 번호, 상품명, 상품 설명, 가격, 재고량, 이미지 경로
		query = String.format("update Product set productNum = '%d', productName = '%s', "
				+ "productExplanation = '%s', productPrice = '%d', productInventory = '%d', productImage = '%s' where productNum = '%d';",
				product.productNum, product.productName, product.productExplanation, product.productPrice, product.productInventory, product.productImage, productBefore.productNum);
		System.out.println("productUpdate: " + query);
		try {
			db.stmt.execute(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.productUpdate : " + e.getMessage());
		}
	}
	
	// 상품 정보 삭제
	public void productDelete(Product product) {
		db.connectDB();
		query = String.format("delete from Product where productNum = '%d';", product.productNum);
		System.out.println("productDelete: " + query);
		try {
			db.stmt.executeUpdate(query);
			db.disconnectDB();
		} catch (Exception e) {
			System.out.println("에러.productDelete : " + e.getMessage());
		}
	}
	
	// 상품 정보 조회
	public ArrayList<Product> productSelect(Integer productNum, String productName) {
		// 1. 상품번호로 조회
		// 2. 상품명으로 조회
		// 3. 상품 전체 조회
		ArrayList<Product> list = new ArrayList<Product>(); // 사용자 정보를 담을 list 생성
		db.connectDB();
		if(productNum != null) {
//			query = String.format("select * from Product where productNum = '%d';", productNum);
			query = "select * from Product where productNum like '%" + String.format("%d", productNum) + "%';";
		} else if(productName != null) {
//			query = String.format("select * from Product where productName = '%s';", productName);
			query = "select * from Product where productName like '%" + productName + "%';";
		} else {
			query = String.format("select * from Product;");
		}
		System.out.println("productSelect: " + query);
		
		try {
			ResultSet result = db.stmt.executeQuery(query);
			while(result.next()) {
				// 상품 번호, 상품명, 상품 설명, 가격, 재고량, 이미지 경로
				Product product = new Product(result.getInt("productNum"), result.getString("productName"), result.getString("productExplanation"),
						result.getInt("productPrice"), result.getInt("productInventory"), result.getString("productImage"));
				list.add(product);
			}
			db.disconnectDB();
			return list;
		} catch (Exception e) {
			System.out.println("에러.productSelect : " + e.getMessage());
			return null;
		}
	}
}
