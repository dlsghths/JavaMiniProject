package dto;

public class UserSave {
	// 찜하기
	public String userId;
	public int productNum; // 상품 번호
	public String productName; // 상품명
	public int productPrice; // 상품 가격
	public String productImage; // 상품 이미지 경로
	
	public UserSave() {
	
	}
	
	public UserSave(String userId, int productNum, String productName, int productPrice, String productImage) {
		this.userId = userId;
		this.productNum = productNum;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = productImage;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
