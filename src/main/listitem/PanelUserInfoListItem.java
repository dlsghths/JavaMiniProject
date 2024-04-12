package main.listitem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dto.User;
import dto.UserBasket;

public class PanelUserInfoListItem {
	static final int itemXsize = 1700;
	static final int itemYsize = 40;
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 2);
	Border emptyBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);
	
	public PanelUserInfoListItem(JPanel panelList, JPanel parentPanel, ArrayList<User> userList, CardLayout cardLayout) {
		for(int i = 0; i < userList.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setPreferredSize(new Dimension(itemXsize, itemYsize));
			panelItem.setBorder(lineBorder);
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.X_AXIS));
			
			JLabel userId = new JLabel("아이디 : " + userList.get(i).userId);
			userId.setAlignmentX(Component.RIGHT_ALIGNMENT);
			userId.setBorder(emptyBorder);
			setLabelFont(userId);
			
			JLabel userName = new JLabel("이름 : " + userList.get(i).userName);
			userName.setAlignmentX(Component.RIGHT_ALIGNMENT);
			userName.setBorder(emptyBorder);
			setLabelFont(userName);
			
			JLabel userBirthday = new JLabel("생년월일 : " + Integer.toString(userList.get(i).getBirthday()));
			userBirthday.setAlignmentX(Component.RIGHT_ALIGNMENT);
			userBirthday.setBorder(emptyBorder);
			setLabelFont(userBirthday);
			
			JLabel userAddress = new JLabel("주소 : " + userList.get(i).getAddress());
			userAddress.setAlignmentX(Component.RIGHT_ALIGNMENT);
			userAddress.setBorder(emptyBorder);
			setLabelFont(userAddress);
			
			JLabel userPhoneNum = new JLabel("전화번호 : " + userList.get(i).getPhoneNumber());
			userPhoneNum.setAlignmentX(Component.RIGHT_ALIGNMENT);
			userPhoneNum.setBorder(emptyBorder);
			setLabelFont(userPhoneNum);
			
			panelItem.add(userId);
			panelItem.add(Box.createHorizontalStrut(60));
			panelItem.add(userName);
			panelItem.add(Box.createHorizontalStrut(60));
			panelItem.add(userBirthday);
			panelItem.add(Box.createHorizontalStrut(60));
			panelItem.add(userAddress);
			panelItem.add(Box.createHorizontalStrut(60));
			panelItem.add(userPhoneNum);
			panelList.add(panelItem);
		}
	}
	
	public void setLabelFont(JLabel label) {
		Font font = new Font(label.getFont().getName(), Font.BOLD, 18);
		label.setFont(font);
	}
}
