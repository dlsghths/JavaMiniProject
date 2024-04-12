package admin;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.PurchaseHistoryDao;
import dto.PurchaseHistory;
import main.MainView;
import main.listitem.PanelpurchaseHistoryListItem;

public class AdminPurchaseHistoryView {
	JPanel panelPurchaseHistory;
	JScrollPane scrollPane;
	JPanel parentPanel;
	PanelpurchaseHistoryListItem panelPurchaseHistoryItem;
	ArrayList<PurchaseHistory> purchaseHistoryList;

	public AdminPurchaseHistoryView(JPanel parentPanel, CardLayout cardLayout) {
		panelPurchaseHistory = new JPanel(new FlowLayout());
		this.parentPanel = parentPanel;
		purchaseHistoryList = new ArrayList<PurchaseHistory>();

		// 스크롤 기능 생성
		scrollPane = new JScrollPane(panelPurchaseHistory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("AdminPurchaseHistoryView", scrollPane);
	}

	public void show(CardLayout cardLayout) {
		panelPurchaseHistory.removeAll();

		purchaseHistoryList = getPurchaseHistorytList();
		panelPurchaseHistoryItem = new PanelpurchaseHistoryListItem(panelPurchaseHistory, parentPanel, purchaseHistoryList, cardLayout);

		panelPurchaseHistory.setPreferredSize(new Dimension(1700, purchaseHistoryList.size() * 200 + 40));
		MainView.frameRefrash();
	}

	private ArrayList<PurchaseHistory> getPurchaseHistorytList() {
		PurchaseHistoryDao purchaseHistoryDao = PurchaseHistoryDao.getInstance();
		ArrayList<PurchaseHistory> purchaseHistoryList = new ArrayList<PurchaseHistory>();
		purchaseHistoryList = purchaseHistoryDao.purchaseSelect(null);
		return purchaseHistoryList;
	}
}
