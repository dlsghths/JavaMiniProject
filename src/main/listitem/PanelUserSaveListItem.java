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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dao.UserSaveDao;
import dto.UserSave;
import main.MainBottomView;

public class PanelUserSaveListItem {
	static final int itemXsize = 1700;
	static final int itemYsize = 200;
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 2);
	Border emptyBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);
	
	public PanelUserSaveListItem(JPanel panelList, JPanel parentPanel, ArrayList<UserSave> userSaveList, CardLayout cardLayout) {
		for(int i = 0; i < userSaveList.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setPreferredSize(new Dimension(itemXsize, itemYsize));
			panelItem.setBorder(lineBorder);
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.X_AXIS));
			
			JLabel itemImage = new JLabel(); // 상품 이미지
			setLabelImage(itemImage, userSaveList.get(i).productImage);
			
			JPanel panelName = new JPanel();
			panelName.setLayout(new BoxLayout(panelName, BoxLayout.Y_AXIS));
			panelName.setBorder(emptyBorder);
			JLabel itemNum = new JLabel(Integer.toString(userSaveList.get(i).productNum)); // 상품명
			JLabel itemName = new JLabel(userSaveList.get(i).productName); // 상품명
			panelName.add(itemNum);
			panelName.add(itemName);
			setLabelFont(itemNum);
			setLabelFont(itemName);
			
			JLabel itemPrice = new JLabel("상품가격: " + Integer.toString(userSaveList.get(i).productPrice) + "원");
			itemPrice.setAlignmentX(Component.RIGHT_ALIGNMENT);
			itemPrice.setBorder(emptyBorder);
			setLabelFont(itemPrice);
			
			JButton buttonDelete = new JButton("삭제");
			buttonDelete.setAlignmentX(Component.RIGHT_ALIGNMENT);
			buttonDelete.setPreferredSize(new Dimension(90, 30));
			buttonDelete.setBorder(BorderFactory.createCompoundBorder(lineBorder, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			setButtonFont(buttonDelete);
			
			UserSave userSave = userSaveList.get(i);
			buttonDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					UserSaveDao userSaveDao = UserSaveDao.getInstance();
					userSaveDao.saveDelete(userSave);
					cardLayout.show(parentPanel, "UserSaveView");
					MainBottomView.userSaveView.show(cardLayout);
				}
			});
			
			
			panelItem.add(itemImage);
			panelItem.add(panelName);
			panelItem.add(Box.createHorizontalGlue());
			panelItem.add(itemPrice);
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
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
	
	public void setLabelFont(JLabel label) {
		Font font = new Font(label.getFont().getName(), Font.BOLD, 18);
		label.setFont(font);
	}
}
