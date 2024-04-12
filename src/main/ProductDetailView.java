package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dao.ProductDao;
import dao.UserBasketDao;
import dao.UserSaveDao;
import dto.Product;
import dto.User;
import dto.UserBasket;
import dto.UserSave;

public class ProductDetailView {
	static final int itemXsize = 600;
	static final int itemYsize = 600;
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 2);
	Border emptyBorder = BorderFactory.createEmptyBorder(30, 30, 30, 10);
	int productCount;
	
	public ProductDetailView(JPanel parentPanel, Product product, CardLayout cardLayout) {
		JPanel panelProductDetail = new JPanel(new GridLayout(1, 2));
		JPanel panelLeft = new JPanel(new BorderLayout());
		
		//이미지 라벨 생성및 이미지 추가
		JLabel itemImage = new JLabel(); // 상품 이미지
		itemImage.setHorizontalAlignment(SwingConstants.CENTER);
		setLabelImage(itemImage, product.productImage);
		panelLeft.add(itemImage, BorderLayout.CENTER);
			
		JPanel panelRight = new JPanel();
		panelRight.setLayout(null);
		panelRight.setBorder(emptyBorder);

		JLabel labelName = new JLabel("상품명 : " + product.productName);
		labelName.setBounds(50, 0, 500, 100);
		setLabelFont(labelName);
		
		JLabel labelExplanationTitle = new JLabel("상품설명 : ");	
		labelExplanationTitle.setBounds(50, 60, 500, 100);
		setLabelFont(labelExplanationTitle);
		
		JPanel panelExplanation = new JPanel();
		panelExplanation.setBounds(50, 130, 600, 200);
		panelExplanation.setBorder(lineBorder);
		
		Font font = new Font("textArea", Font.BOLD, 16);
		JTextArea textArea = new JTextArea(product.productExplanation);
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 580, 500);
		textArea.setFont(font);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBackground(null);
		panelExplanation.add(textArea);
		
		JLabel labelPrice = new JLabel("가격 : " + Integer.toString(product.getProductPrice())+ "원");
		setLabelFont(labelPrice);
		labelPrice.setBounds(50, 110, 500, 500);
		JLabel labelInventory = new JLabel("남은 수량 : " + product.productInventory);
		setLabelFont(labelInventory);
		labelInventory.setBounds(50, 160, 500, 500);
		
		JPanel panelCount = new JPanel();
		panelCount.setLayout(new BoxLayout(panelCount, BoxLayout.X_AXIS));
		panelCount.setBounds(180, 500, 300, 30);
		
		JLabel labelCount = new JLabel("구매 수량:    ");
		setLabelFont(labelCount);
		panelCount.add(labelCount);
		
		productCount = 1;
		JLabel itemCount = new JLabel("    " + productCount + "    ");
		setLabelFont(itemCount);
		
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
				itemCount.setText("    " + productCount + "    ");
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
				itemCount.setText("    " + productCount + "    ");
			}
		});
		
		panelCount.add(decreaseButton);
		panelCount.add(itemCount);
		panelCount.add(increaseButton);
		
		JButton buttonBasket = new JButton("장바구니 추가");
		setButtonFont(buttonBasket);
		buttonBasket.setBounds(130, 600, 200, 50);
		buttonBasket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 장바구니 추가
				// 계정로그인 안되어있으면 로그인 화면으로 이동
				if(MainView.getLoginUser().userId != null) {
					// 계정 로그인 되어 있는 경우
					basketInsert(product, MainView.getLoginUser().userId, productCount);	
					JOptionPane.showMessageDialog(null, "장바구니 목록에 추가했습니다.", "장바구니 추가", JOptionPane.INFORMATION_MESSAGE);
					cardLayout.show(parentPanel, "clothesList");
					MainBottomView.show(cardLayout);
				} else {
					// 계정 로그인 안되어 있는 경우 로그인 화면으로 이동
					MainBottomView.returnLoginView(cardLayout);
				}
			}
		});
		
		JButton buttonSave = new JButton("찜하기");
		setButtonFont(buttonSave);
		buttonSave.setBounds(350, 600, 200, 50);
		buttonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 장바구니 추가
				// 계정로그인 안되어있으면 로그인 화면으로 이동
				if(MainView.getLoginUser().userId != null) {
					// 계정 로그인 되어 있는 경우
					saveInsert(product, MainView.getLoginUser().userId, productCount);	
					JOptionPane.showMessageDialog(null, "찜하기 목록에 추가했습니다.", "찜하기", JOptionPane.INFORMATION_MESSAGE);
					cardLayout.show(parentPanel, "clothesList");
					MainBottomView.show(cardLayout);
				} else {
					// 계정 로그인 안되어 있는 경우 로그인 화면으로 이동
					MainBottomView.returnLoginView(cardLayout);
				}
			}
		});

		panelRight.add(labelName);
		panelRight.add(labelExplanationTitle);
		panelRight.add(panelExplanation);
		panelRight.add(labelPrice);
		panelRight.add(labelInventory);
		panelRight.add(panelCount);
		panelRight.add(buttonBasket);
		panelRight.add(buttonSave);
		
		panelProductDetail.add(panelLeft);
		panelProductDetail.add(panelRight);
		
		parentPanel.add(panelProductDetail, "ProductDetailView");
	}
	
	public void show() {
	
	}
	
	// 라벨에 이미지 넣기
	public void setLabelImage(JLabel label, String url) {
		Image image = new ImageIcon(url).getImage();
		
		// 이미지의 사이즈를 변경
		Image changeImage = image.getScaledInstance(itemXsize, itemYsize, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		
		label.setIcon(changeIcon);
	}
	
	public void basketInsert(Product product, String userId, int productCount) {
		UserBasket userBasket = new UserBasket();
		userBasket.userId = userId;
		userBasket.productNum = product.productNum;
		userBasket.productName = product.productName;
		userBasket.productPrice = product.productPrice;
		userBasket.productCount = productCount;
		userBasket.productImage = product.productImage;
		
		UserBasketDao userBasketDao = UserBasketDao.getInstance();
		
		userBasketDao.basketInsert(userBasket);
	}
	
	public void saveInsert(Product product, String userId, int productCount) {
		UserSave userSave = new UserSave();
		userSave.userId = userId;
		userSave.productNum = product.productNum;
		userSave.productName = product.productName;
		userSave.productPrice = product.productPrice;
		userSave.productImage = product.productImage;
		
		UserSaveDao userSaveDao = UserSaveDao.getInstance();
		userSaveDao.saveInsert(userSave);
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
