package dto;

public class Product {
	// 상품관리
	public int productNum; // 상품 번호	
	public String productName; // 상품명
	public String productExplanation; // 상품 설명
	public int productPrice; // 상품 가격
	public int productInventory; // 상품 재고량
	public String productImage; // 상품 이미지 경로
	
	public Product() {
	}
	
	public Product(int productNum, String productName, String productExplanation,
			int productPrice, int productInventory, String productImage) {
		this.productNum = productNum;
		this.productName = productName;
		this.productExplanation = productExplanation;
		this.productPrice = productPrice;
		this.productInventory = productInventory;
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
	public String getProductExplanation() {
		return productExplanation;
	}
	public void setProductExplanation(String productExplanation) {
		this.productExplanation = productExplanation;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
