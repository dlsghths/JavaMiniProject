package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.ProductDao;
import dto.Product;
import main.listitem.PanelClothesListItem;

public class PanelClothesList extends JPanel {
	JPanel clothesListPanel;
	JScrollPane scrollPane;
	PanelClothesListItem panelClothesListItem;
	
	public PanelClothesList(JPanel parentPanel, String productNum, JScrollPane scrollPane, CardLayout cardLayout) {
		clothesListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		clothesListPanel.setBackground(Color.CYAN);
		
		ArrayList<Product> clothesList = new ArrayList<Product>();
		if(productNum == "") {
			clothesList = getClothesList();
		} else {
			clothesList = getClothesList(Integer.parseInt(productNum));
		}
		
		panelClothesListItem = new PanelClothesListItem(clothesListPanel, parentPanel, clothesList, cardLayout);
		
		// 스크롤 기능 생성
		scrollPane = new JScrollPane(clothesListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		int scrollHeight = (scrollPane.getPreferredSize().width / 1400);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("clothesList", scrollPane);
		
		clothesListPanel.setPreferredSize(new Dimension(500, scrollHeight * 420 + 20));
	}
	
	// 상품 리스트 목록 가져오기
	public ArrayList<Product> getClothesList(int productNum) {
		ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = new ArrayList<Product>();
		productList = productDao.productSelect(productNum, null);
		
		return productList;
	}
	
	// 상품 리스트 목록 가져오기
		public ArrayList<Product> getClothesList() {
			ProductDao productDao = new ProductDao();
			ArrayList<Product> productList = new ArrayList<Product>();
			productList = productDao.productSelect(null, null);
			
			return productList;
		}
}
