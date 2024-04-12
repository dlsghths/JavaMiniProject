package admin;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import dao.UserDao;
import dto.User;
import main.MainView;
import main.listitem.PanelUserInfoListItem;

public class AdminUserInfoView {
	JPanel paneladminUserInfo;
	JScrollPane scrollPane;
	JPanel parentPanel;
	JLabel label;
	PanelUserInfoListItem panelUserInfoListItem;
	ArrayList<User> userList;

	public AdminUserInfoView(JPanel parentPanel, CardLayout cardLayout) {
		paneladminUserInfo = new JPanel(new FlowLayout());
		this.parentPanel = parentPanel;
		userList = new ArrayList<User>();

		// 스크롤 기능 생성
		scrollPane = new JScrollPane(paneladminUserInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("AdminUserInfoView", scrollPane);
	}

	public void show(CardLayout cardLayout) {
		paneladminUserInfo.removeAll();

		userList = getUserList();
		panelUserInfoListItem = new PanelUserInfoListItem(paneladminUserInfo, parentPanel, userList, cardLayout);

		paneladminUserInfo.setPreferredSize(new Dimension(1700, userList.size() * 50 + 40));
		MainView.frameRefrash();
	}

	private ArrayList<User> getUserList() {
		UserDao userDao = UserDao.getInstance();
		ArrayList<User> userList = new ArrayList<User>();
		userList = userDao.userSelect(null, null);
		
		return userList;
	}
}
