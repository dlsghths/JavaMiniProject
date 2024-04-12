package user.mypage;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.UserSaveDao;
import dto.UserSave;
import main.MainView;
import main.listitem.PanelUserSaveListItem;

public class UserSaveView {
	JPanel panelUserSave;
	JScrollPane scrollPane;
	JPanel parentPanel;
	PanelUserSaveListItem panelUserSaveListItem;
	ArrayList<UserSave> userSaveList;
	
	public UserSaveView(JPanel parentPanel, CardLayout cardLayout) {
		panelUserSave = new JPanel(new FlowLayout());
		this.parentPanel = parentPanel;
		userSaveList = new ArrayList<UserSave>();
		
		// 스크롤 기능 생성
		scrollPane = new JScrollPane(panelUserSave, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
		parentPanel.add("UserSaveView", scrollPane);
	}
	
	public void show(CardLayout cardLayout) {
		panelUserSave.removeAll();
		
		userSaveList = getUserSaveList(MainView.getLoginUser().userId);
		panelUserSaveListItem = new PanelUserSaveListItem(panelUserSave, parentPanel, userSaveList, cardLayout);
		
		panelUserSave.setPreferredSize(new Dimension(1700, userSaveList.size() * 200 + 40));
		MainView.frameRefrash();
	}
	
	public ArrayList<UserSave> getUserSaveList(String userId) {
		UserSaveDao userSaveDao = UserSaveDao.getInstance();
		ArrayList<UserSave> saveList = new ArrayList<UserSave>();
		saveList = userSaveDao.saveSelect(userId);
		
		return saveList;
	}
}
