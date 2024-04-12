package user.mypage;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.PurchaseHistoryDao;
import dto.PurchaseHistory;
import main.MainView;
import main.listitem.PanelPurchaseListItem;

public class UserPurchaseView {
	JPanel panelUserPurchase;
	JScrollPane scrollPane;
	JPanel parentPanel;
	PanelPurchaseListItem panelPurchaseListItem;
	ArrayList<PurchaseHistory> purchaseList;
	
	public UserPurchaseView(JPanel parentPanel, CardLayout cardLayout) {
		panelUserPurchase = new JPanel(new FlowLayout());
		this.parentPanel = parentPanel;
		purchaseList = new ArrayList<PurchaseHistory>();
		
		// 스크롤 기능 생성
		scrollPane = new JScrollPane(panelUserPurchase, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("UserPurchaseView", scrollPane);
	}
	
	public void show(CardLayout cardLayout) {
		panelUserPurchase.removeAll();
		
		purchaseList = getPurchaseList(MainView.getLoginUser().userId);
		panelPurchaseListItem = new PanelPurchaseListItem(panelUserPurchase, parentPanel, purchaseList, cardLayout);
		
		panelUserPurchase.setPreferredSize(new Dimension(1700, purchaseList.size() * 200 + 40));
		MainView.frameRefrash();
	}
	
	public ArrayList<PurchaseHistory> getPurchaseList(String userId) {
		PurchaseHistoryDao userPurchaseHistoryDao = PurchaseHistoryDao.getInstance();
		ArrayList<PurchaseHistory> purchaseList = new ArrayList<PurchaseHistory>();
		purchaseList = userPurchaseHistoryDao.purchaseSelect(userId);
		
		return purchaseList;
	}
}
