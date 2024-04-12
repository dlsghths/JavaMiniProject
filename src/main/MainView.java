package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.User;

public class MainView extends JFrame {
	
	// 화면을 구성하는 메인화면의 크기 고정
	static final int mainXSize = 1800;
	static final int mainYSize = 1000;
	static User loginUserInfo;
	
	static MainTopView mainTopView;
	static MainView mainFrame;
	
	public static void main(String[] args) {
		loginUserInfo = new User();
		mainFrame = new MainView();
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		mainFrame.setSize(mainXSize, mainYSize);

		CardLayout cardLayout = new CardLayout();
		JScrollPane scrollPane = new JScrollPane();
		mainTopView = new MainTopView(mainFrame, cardLayout);
		MainBottomView mainBottomView = new MainBottomView(mainFrame, cardLayout, scrollPane);
		
		frameSetting(mainFrame);
	}
	
	public static void frameSetting(JFrame mainFrame) {
		mainFrame.setLocationRelativeTo(null); // 가운데 고정
		mainFrame.setResizable(false); // 화면 크기 조절 false
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void setLoginUser(User user) {
		loginUserInfo = user;
		mainTopView.changeLoginButton(loginUserInfo);
	}
	
	public static User getLoginUser() {
		return loginUserInfo;
	}
	
	public static void frameRefrash() {
		mainFrame.revalidate();
		mainFrame.repaint();
	}
}