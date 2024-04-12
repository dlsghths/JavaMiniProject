package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import admin.AdminPurchaseHistoryView;
import admin.AdminUserInfoView;
import admin.ProductManagementView;
import admin.ProductRegistrationView;
import user.login.LoginView;
import user.mypage.CancelMembershipView;
import user.mypage.MemberInfoEditView;
import user.mypage.UserBasketView;
import user.mypage.UserPurchaseView;
import user.mypage.UserSaveView;

public class MainBottomView extends JPanel {
	static JPanel panelBottom;
	static PanelClothesList panelItemList;
	static JScrollPane scrollPane;
	static JFrame mainFrame;
	
	public static LoginView loginView;
	
	// 마이페이지 관련 View
	static MemberInfoEditView memberInfoEditView;
	public static UserBasketView userBasketView;
	public static UserSaveView userSaveView;
	public static UserPurchaseView userPurchaseView;
	static CancelMembershipView cancelMembershipView;
	
	static AdminPurchaseHistoryView adminPurchaseHistoryView;
	static AdminUserInfoView adminUserInfoView;
	static ProductManagementView productManagementView;
	static ProductRegistrationView productRegistrationView;
	
	public MainBottomView(JFrame mainFrame, CardLayout cardLayout, JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
		this.mainFrame = mainFrame;
		
		panelBottom = new JPanel();
		panelBottom.setLayout(cardLayout);
		panelBottom.setPreferredSize(new Dimension(0, 850));
		mainFrame.add(panelBottom);
		
		panelItemList = new PanelClothesList(panelBottom, "0101", scrollPane, cardLayout);
		
		// 로그인 화면 환경설정
		loginView = new LoginView(panelBottom, cardLayout);
		
		// 마이페이지 화면 환경설정
		memberInfoEditView = new MemberInfoEditView(panelBottom);
		userBasketView = new UserBasketView(panelBottom, cardLayout);
		userSaveView = new UserSaveView(panelBottom, cardLayout);
		userPurchaseView = new UserPurchaseView(panelBottom, cardLayout);
		cancelMembershipView = new CancelMembershipView(panelBottom, cardLayout);
		
		// 관리자 화면 환경설정
		adminUserInfoView = new AdminUserInfoView(panelBottom, cardLayout);
		adminPurchaseHistoryView = new AdminPurchaseHistoryView(panelBottom, cardLayout);
		productManagementView = new ProductManagementView(panelBottom, scrollPane, cardLayout);
		productRegistrationView = new ProductRegistrationView(panelBottom, cardLayout);
	
		cardLayout.show(panelBottom, "clothesList");
	}
	
	public static void show(CardLayout cardLayout) {
		change(mainFrame, cardLayout, "0101");
	}
	
	public static void change(JFrame mainFrame, CardLayout cardLayout, String productNum) {
		panelBottom.remove(scrollPane);	
		panelItemList = new PanelClothesList(panelBottom, productNum, scrollPane, cardLayout);
		
		cardLayout.show(panelBottom, "clothesList");
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	 public static void buttonClickListener(MouseEvent e, JFrame mainFrame, CardLayout cardLayout) {
		 JButton button = (JButton) e.getSource();
		 if(button.getParent().getWidth() == 1000) {
			cardLayout.show(panelBottom, "clothesList");
			switch (button.getText()) {
			case "TOP": {
				change(mainFrame, cardLayout, "0101");
				break;
			}
			case "OUTER": {
				change(mainFrame, cardLayout, "0102");
				break;
			}
			case "PANTS": {
				change(mainFrame, cardLayout, "0103");
				break;
			}
			case "SHOES": {
				change(mainFrame, cardLayout, "0104");
				break;
			}
			case "ACCESSORY": {
				change(mainFrame, cardLayout, "0105");
				break;
			}
			
			case "회원정보 수정": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "MemberInfoEditView");
				memberInfoEditView.show(MainView.getLoginUser());
				break;
			}
			case "장바구니": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "UserBasketView");
				userBasketView.show(cardLayout);
				break;
			}
			case "찜하기": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "UserSaveView");
				userSaveView.show(cardLayout);
				break;
			}
			case "구매내역": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "UserPurchaseView");
				userPurchaseView.show(cardLayout);
				break;
			}
			case "회원탈퇴": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "CancelMembershipView");
				cancelMembershipView.show();
				break;
			}
			
			case "사용자정보": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "AdminUserInfoView");
				adminUserInfoView.show(cardLayout);
				break;
			}
			case "사용자 구매내역": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "AdminPurchaseHistoryView");
				adminPurchaseHistoryView.show(cardLayout);
				break;
			}
			case "상품관리": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
//				cardLayout.show(panelBottom, "ProductManagementView");
				change(mainFrame, cardLayout, "");
				break;
			}
			case "상품등록": {
				if(MainView.getLoginUser().userId == null) {
					returnLoginView(cardLayout);
					break;
				}
				cardLayout.show(panelBottom, "ProductRegistrationView");
				break;
			}
			default:
				break;
			}
		} else if(button.getParent().getWidth() == 1400) {
			switch (button.getText()) {
			case "TOP": {
				change(mainFrame, cardLayout, "0201");
				break;
			}
			case "OUTER": {
				change(mainFrame, cardLayout, "0202");
				break;
			}
			case "PANTS": {
				change(mainFrame, cardLayout, "0203");
				break;
			}
			case "DRESS": {
				change(mainFrame, cardLayout, "0204");
				break;
			}
			case "SKIRT": {
				change(mainFrame, cardLayout, "0205");
				break;
			}
			case "SHOES": {
				change(mainFrame, cardLayout, "0206");
				break;
			}
			case "ACCESSORY": {
				change(mainFrame, cardLayout, "0207");
				break;
			}
			default:
				break;
			}
		} else {
			switch (button.getText()) {
			case "LOGIN": {
				cardLayout.show(panelBottom, "LoginView");
				loginView.show();
				break;
			}
			case "LOGOUT": {
				MainView.setLoginUser(null);
				cardLayout.show(panelBottom, "LoginView");
				loginView.show();
				break;
			}
			default:
				break;
			}
		}
	 }
	 
	 public static void returnLoginView(CardLayout cardLayout) {
		cardLayout.show(panelBottom, "LoginView");
		loginView.show();
	 }
}

