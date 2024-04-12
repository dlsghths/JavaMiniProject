package user.mypage;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.PurchaseHistoryDao;
import dao.UserBasketDao;
import dto.PurchaseHistory;
import dto.UserBasket;
import main.MainBottomView;
import main.MainView;
import main.listitem.PanelBasketListItem;

public class UserBasketView {
	JPanel panelUserBasket;
	JScrollPane scrollPane;
	JPanel parentPanel;
	PanelBasketListItem panelBasketListItem;
	ArrayList<UserBasket> basketList;
	
	public UserBasketView(JPanel parentPanel, CardLayout cardLayout) {
		panelUserBasket = new JPanel(new FlowLayout());
		this.parentPanel = parentPanel;
		basketList = new ArrayList<UserBasket>();
		
		// 스크롤 기능 생성
		scrollPane = new JScrollPane(panelUserBasket, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("UserBasketView", scrollPane);
	}
	
	public void show(CardLayout cardLayout) {
		panelUserBasket.removeAll();
		
		basketList = getBasketList(MainView.getLoginUser().userId);
		panelBasketListItem = new PanelBasketListItem(panelUserBasket, parentPanel, basketList, cardLayout);
		
		JButton buttonPurchase = new JButton("구매하기");
		buttonPurchase.setPreferredSize(new Dimension(200, 50));
		buttonPurchase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 구매내역에 넣기
				ArrayList<PurchaseHistory> purchaseHistory = new ArrayList<PurchaseHistory>();
				for(UserBasket userBasket: basketList) {
					PurchaseHistory purchase = new PurchaseHistory(userBasket.userId, userBasket.productNum,
							userBasket.productName, userBasket.productPrice, userBasket.productCount, userBasket.productImage);
					purchaseHistory.add(purchase);
				}
				PurchaseHistoryDao purchaseHistoryDao = PurchaseHistoryDao.getInstance();
				purchaseHistoryDao.purchaseInsert(purchaseHistory);
				
				cardLayout.show(parentPanel, "UserPurchaseView");
				MainBottomView.userPurchaseView.show(cardLayout);
				JOptionPane.showMessageDialog(null, "상품구매에 성공했습니다.\n구매내역 화면으로 이동합니다.", "상품구매", JOptionPane.INFORMATION_MESSAGE);
				
				// 장바구니 목록 비우기
				for(UserBasket userBasket: basketList) {
					UserBasketDao userBasketDao = UserBasketDao.getInstance();
					userBasketDao.basketDelete(userBasket);
				}
			}
		});
		setButtonFont(buttonPurchase);
		panelUserBasket.add(buttonPurchase);
		
		panelUserBasket.setPreferredSize(new Dimension(1700, basketList.size() * 200 + 40));
		MainView.frameRefrash();
	}
	
	public ArrayList<UserBasket> getBasketList(String userId) {
		UserBasketDao userBasketDao = UserBasketDao.getInstance();
		ArrayList<UserBasket> basketList = new ArrayList<UserBasket>();
		basketList = userBasketDao.basketSelect(userId);
		
		return basketList;
	}
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
}
