package dto;

public class PurchaseHistory {
	public String userId; // 사용자 아이디
	public int productNum; // 상품 번호
	public String productName; // 상품명
	public int productPrice; // 상품 가격
	public int productCount; // 상품 수량
	public String productImage; // 상품 이미지 경로
	
	public PurchaseHistory(String userId, int productNum, String productName,
			int productPrice, int productCount, String productImage) {
		this.userId = userId;
		this.productNum = productNum;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productImage = productImage;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
