package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.User;

public class MainTopView extends JPanel {
	JPanel panelTop;
	JPanel panelMen;
	JPanel panelWomen;
	JPanel panelMyPage;
	JPanel panelAdmin;
	
	JButton[] mainButton;
	
	MouseListener mouseListener;
	
	public MainTopView(JFrame mainFrame, CardLayout cardLayout) {
		panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(Color.WHITE);
		panelTop.setPreferredSize(new Dimension(0, 150));
		mainFrame.add(panelTop);
		
		createMouseListener(mainFrame, cardLayout);
		showLogo();
		showButton();
		
		panelMen = new JPanel();
		panelMen.setLayout(new GridLayout(1, 5));
		panelMen.setBounds(400, 70, 1000, 50);
		panelMen.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
		panelMen.setVisible(false);
		showMenSubButton(panelMen);
		
		panelWomen = new JPanel();
		panelWomen.setLayout(new GridLayout(1, 7));
		panelWomen.setBounds(400, 70, 1400, 50);
		panelWomen.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
		panelWomen.setVisible(false);
		showWomenSubButton(panelWomen);
		
		panelMyPage = new JPanel();
		panelMyPage.setLayout(new GridLayout(1, 6));
		panelMyPage.setBounds(752, 70, 1000, 50);
		panelMyPage.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
		panelMyPage.setVisible(false);
		showMyPageSubButton(panelMyPage);
		
		panelAdmin = new JPanel();
		panelAdmin.setLayout(new GridLayout(1, 4));
		panelAdmin.setBounds(752, 70, 1000, 50);
		panelAdmin.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
		panelAdmin.setVisible(false);
		showAdminPageSubButton(panelAdmin);
		
		panelTop.add(panelMen);
		panelTop.add(panelWomen);
		panelTop.add(panelMyPage);
		panelTop.add(panelAdmin);
		
		panelMen.addMouseListener(mouseListener);
		panelWomen.addMouseListener(mouseListener);
		panelMyPage.addMouseListener(mouseListener);
		panelAdmin.addMouseListener(mouseListener);
	}
	
	public void createMouseListener(JFrame mainFrame, CardLayout cardLayout) {
		mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스가 윈도우 밖으로 나갔을 경우
				if(e.getSource().getClass() == JButton.class) {
					JButton button = (JButton) e.getSource();
					if(button.getText() == "MEN") {
						panelMen.setVisible(false);
					} else if(button.getText() == "WOMEN") {
						panelWomen.setVisible(false);
					} else if(button.getText() == "MY PAGE") {
						panelMyPage.setVisible(false);
					} else if(button.getText() == "ADMIN PAGE") {
						panelAdmin.setVisible(false);
					}
				}
				if(e.getSource() == panelMen) {
					panelMen.setVisible(false);
				} else if(e.getSource() == panelWomen) {
					panelWomen.setVisible(false);
				} else if(e.getSource() == panelMyPage) {
					panelMyPage.setVisible(false);
				} else if(e.getSource() == panelAdmin) {
					panelAdmin.setVisible(false);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스가 윈도우 안에 왔을 경우
				if(e.getSource().getClass() == JButton.class) {
					JButton button = (JButton) e.getSource();
					if(button.getParent().getWidth() == 1000 || button.getText() == "MEN" || button.getText() == "ADMIN PAGE" || button.getText() == "MY PAGE") {				
						switch (button.getText()) {
						case "MEN", "TOP", "OUTER", "PANTS", "SHOES", "ACCESSORY": {
							panelMen.setVisible(true);
							break;
						}
						case "MY PAGE", "회원정보 수정", "장바구니", "찜하기", "구매내역", "회원탈퇴": {
							panelMyPage.setVisible(true);
							break;
						}
						case "ADMIN PAGE", "사용자정보", "사용자 구매내역", "상품관리", "상품등록": {							
							panelAdmin.setVisible(true);
							break;							
						}
						default:
							break;
						}
					} else if((button.getParent().getWidth() == 1400 || button.getText() == "WOMEN") ) {
						switch (button.getText()) {
						case "WOMEN", "TOP", "OUTER", "PANTS", "DRESS", "SKIRT", "SHOES", "ACCESSORY": {
							panelWomen.setVisible(true);
							break;
						}
						default:
							break;
						}
					} 
				}
				
				
				if(e.getSource() == panelMen) {
					panelMen.setVisible(true);
				} else if(e.getSource() == panelWomen) {
					panelWomen.setVisible(true);
				} else if(e.getSource() == panelMyPage) {
					panelMyPage.setVisible(true);
				} else if(e.getSource() == panelAdmin) {
					panelAdmin.setVisible(true);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// 마우스가 클릭했을 경우
				if(e.getSource().getClass() == JButton.class) {
					JButton button = (JButton) e.getSource();
					MainBottomView.buttonClickListener(e, mainFrame, cardLayout);
				}
			}
		};
	}
	
	public void buttonListenerSetting(JButton[] buttons) {
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].addMouseListener(mouseListener);
		}
	}
	
	public void showButton() {
		String[] buttonName = {"LOGIN", "MY PAGE", "ADMIN PAGE", "MEN", "WOMEN"};
		mainButton = new JButton[5];
		for(int i = 0; i < buttonName.length; i++) {
			mainButton[i] = new JButton(buttonName[i]);
			setButtonFont(mainButton[i]);
			panelTop.add(mainButton[i]);
		}
		// 로그인, 마이페이지, 고객센터
		mainButton[0].setBounds(1150, 20, 200, 50);
		mainButton[1].setBounds(1350, 20, 200, 50);
		mainButton[2].setBounds(1350, 20, 200, 50);
		
		// 의류 남성, 여성
		mainButton[3].setBounds(400, 20, 200, 50);
		mainButton[4].setBounds(600, 20, 200, 50);
		
		buttonListenerSetting(mainButton);
	}
	
	// 남성 의류에 대한 세부 버튼 세팅
	public void showMenSubButton(JPanel subPanel) {
		String[] buttonName = {"TOP", "OUTER", "PANTS", "SHOES", "ACCESSORY"};
		JButton[] button = new JButton[5];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			setButtonFont(button[i]);
			subPanel.add(button[i]);
		}
		buttonListenerSetting(button);
	}
	
	// 여성 의류에 대한 세부 버튼 세팅
	public void showWomenSubButton(JPanel subPanel) {
		String[] buttonName = {"TOP", "OUTER", "PANTS", "DRESS", "SKIRT", "SHOES", "ACCESSORY"};
		JButton[] button = new JButton[7];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			setButtonFont(button[i]);
			subPanel.add(button[i]);
		}
		buttonListenerSetting(button);
	}
	
	// 마이페이지에 대한 세부 버튼 세팅
	public void showMyPageSubButton(JPanel subPanel) {
		String[] buttonName = {"회원정보 수정", "장바구니", "찜하기", "구매내역", "회원탈퇴"};
		JButton[] button = new JButton[5];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			setButtonFont(button[i]);
			subPanel.add(button[i]);
		}
		buttonListenerSetting(button);
	}
	
	public void showAdminPageSubButton(JPanel subPanel) {
		String[] buttonName = {"사용자정보", "사용자 구매내역", "상품관리", "상품등록"};
		JButton[] button = new JButton[4];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			setButtonFont(button[i]);
			subPanel.add(button[i]);
		}
		buttonListenerSetting(button);
	}
	
	public void showLogo() {
		JLabel mainLogo = new JLabel();
		Image image = new ImageIcon("image/Logo/logo.jpg").getImage();
		Image changeImage = image.getScaledInstance(400, MainView.mainYSize / 8, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		
		mainLogo.setIcon(changeIcon);
		panelTop.add(mainLogo);
		mainLogo.setBounds(0, -5, 400, MainView.mainYSize / 8);
	}
	
	public void changeLoginButton(User user) {
		if(user == null) {
			mainButton[0].setText("LOGIN");
			mainButton[1].setVisible(true);
			mainButton[2].setVisible(false);
		} else if(user.isAdmin == true) {
			mainButton[0].setText("LOGOUT");
			mainButton[1].setVisible(false);
			mainButton[2].setVisible(true);
		} else {
			mainButton[0].setText("LOGOUT");	
			mainButton[1].setVisible(true);
			mainButton[2].setVisible(false);
		}
	}
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
}
