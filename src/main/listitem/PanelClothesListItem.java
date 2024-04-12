package main.listitem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.Product;
import main.ProductDetailView;

public class PanelClothesListItem extends JPanel {
	
	static final int itemXsize = 330;
	static final int itemYsize = 400;
	
	public PanelClothesListItem(JPanel panelList, JPanel parentPanel, ArrayList<Product> clothesList, CardLayout cardLayout) {
		for(int i = 0; i < clothesList.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setPreferredSize(new Dimension(itemXsize, itemYsize));
			panelItem.setBackground(Color.yellow);
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
			
			JLabel itemImage = new JLabel(); // 상품 이미지
			setLabelImage(itemImage, clothesList.get(i).productImage);
			JLabel itemName = new JLabel(clothesList.get(i).productName); // 상품명
			
			setLabelSetting(itemName);
				
			JLabel itemPrice = new JLabel(String.valueOf(clothesList.get(i).productPrice + " 원"), JLabel.RIGHT); // 상품가격
			setLabelSetting(itemPrice);
			
			panelItem.add(itemImage);
			panelItem.add(itemName);
			panelItem.add(itemPrice);
			panelList.add(panelItem);
			
			int productNum = clothesList.get(i).productNum;
			Product select_product = clothesList.get(i);
			
			panelItem.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// 상품 세부 정보 화면으로 이동
					new ProductDetailView(parentPanel,select_product, cardLayout);
					cardLayout.show(parentPanel, "ProductDetailView");
				}
			});
		}
	}

	// 라벨에 이미지 넣기
	public void setLabelImage(JLabel label, String url) {
		Image image = new ImageIcon(url).getImage();
		
		// 이미지의 사이즈를 변경
		Image changeImage = image.getScaledInstance(itemXsize, 250, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		
		label.setIcon(changeIcon);
	}
	
	// label 기본 설정
	public void setLabelSetting(JLabel label) {
		label.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		
		Font font = new Font(label.getFont().getName(), Font.BOLD, 18);
		label.setFont(font);
	}
}
