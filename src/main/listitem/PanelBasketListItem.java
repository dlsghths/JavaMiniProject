package main.listitem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dao.UserBasketDao;
import dao.UserSaveDao;
import dto.UserBasket;
import dto.UserSave;
import main.MainBottomView;

public class PanelBasketListItem {
	static final int itemXsize = 1700;
	static final int itemYsize = 200;
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 2);
	Border emptyBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);
	int productCount;
	
	public PanelBasketListItem(JPanel panelList, JPanel parentPanel, ArrayList<UserBasket> basketList, CardLayout cardLayout) {
		for(int i = 0; i < basketList.size(); i++) {
		
			JPanel panelItem = new JPanel();
			panelItem.setPreferredSize(new Dimension(itemXsize, itemYsize));
			panelItem.setBorder(lineBorder);
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.X_AXIS));
			
			JLabel itemImage = new JLabel(); // 상품 이미지
			setLabelImage(itemImage, basketList.get(i).productImage);
			
			JPanel panelName = new JPanel();
			panelName.setLayout(new BoxLayout(panelName, BoxLayout.Y_AXIS));
			panelName.setBorder(emptyBorder);
			JLabel itemNum = new JLabel(Integer.toString(basketList.get(i).productNum)); // 상품명
			JLabel itemName = new JLabel(basketList.get(i).productName); // 상품명
			panelName.add(itemNum);
			panelName.add(itemName);
			setLabelFont(itemNum);
			setLabelFont(itemName);
			
			JLabel itemPrice = new JLabel("상품가격: " + Integer.toString(basketList.get(i).productPrice) + "원");
			itemPrice.setAlignmentX(Component.RIGHT_ALIGNMENT);
			itemPrice.setBorder(emptyBorder);
			setLabelFont(itemPrice);
			
			JLabel priceSum = new JLabel();
			priceSum.setAlignmentX(Component.RIGHT_ALIGNMENT);
			priceSum.setBorder(emptyBorder);
			setLabelFont(priceSum);
			
			productCount = basketList.get(i).productCount;
			JLabel itemCount = new JLabel("주문수량: \n" + productCount + " 개");
			itemCount.setAlignmentX(Component.RIGHT_ALIGNMENT);
			itemCount.setBorder(emptyBorder);
			setLabelFont(itemCount);
			
			int itemPriceDefault = basketList.get(i).productPrice;
			changePriceSum(priceSum, itemPriceDefault, productCount);
			
			JButton decreaseButton = new JButton("<");
			decreaseButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
			decreaseButton.setPreferredSize(new Dimension(45, 30));
			decreaseButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(itemCount.getText());

			        while (matcher.find()) {
			            String extractedNumber = matcher.group(); // 추출된 숫자
			            productCount = Integer.parseInt(extractedNumber);
			        }
			        
					productCount -= 1;
					itemCount.setText("주문수량: \n" + productCount + " 개");
					changePriceSum(priceSum, itemPriceDefault, productCount);		
				}
			});
			JButton increaseButton = new JButton(">");
			increaseButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
			increaseButton.setPreferredSize(new Dimension(45, 30));
			increaseButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(itemCount.getText());

			        while (matcher.find()) {
			            String extractedNumber = matcher.group(); // 추출된 숫자
			            productCount = Integer.parseInt(extractedNumber);
			        }

					productCount += 1;
					itemCount.setText("주문수량: \n" + productCount + " 개");
					changePriceSum(priceSum, itemPriceDefault, productCount);
				}
			});
			
			JButton buttonDelete = new JButton("삭제");
			buttonDelete.setAlignmentX(Component.RIGHT_ALIGNMENT);
			buttonDelete.setPreferredSize(new Dimension(90, 30));
			buttonDelete.setBorder(BorderFactory.createCompoundBorder(lineBorder, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			setButtonFont(buttonDelete);
			
			UserBasket userBasket = basketList.get(i);
			buttonDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					UserBasketDao userBasketDao = UserBasketDao.getInstance();
					userBasketDao.basketDelete(userBasket);
					cardLayout.show(parentPanel, "UserBasketView");
					MainBottomView.userBasketView.show(cardLayout);
				}
			});

			panelItem.add(itemImage);
			panelItem.add(panelName);
			panelItem.add(Box.createHorizontalGlue());
			panelItem.add(decreaseButton);
			panelItem.add(itemCount);
			panelItem.add(increaseButton);
			panelItem.add(Box.createHorizontalStrut(100));
			panelItem.add(priceSum);
			panelItem.add(buttonDelete);
			panelItem.add(Box.createHorizontalStrut(20));
		
			panelList.add(panelItem);
		}
	}
	
	// 라벨에 이미지 넣기
	public void setLabelImage(JLabel label, String url) {
		Image image = new ImageIcon(url).getImage();
		
		// 이미지의 사이즈를 변경
		Image changeImage = image.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		
		label.setIcon(changeIcon);
	}
	
	public void changePriceSum(JLabel priceSum, int price, int count) {
		int sum = price * count;
		priceSum.setText("구매가격: " + sum + "원");
	}
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
	
	public void setLabelFont(JLabel label) {
		Font font = new Font(label.getFont().getName(), Font.BOLD, 18);
		label.setFont(font);
	}
}
