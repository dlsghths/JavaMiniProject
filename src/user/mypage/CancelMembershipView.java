package user.mypage;


import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.UserDao;
import main.MainBottomView;
import main.MainView;

public class CancelMembershipView {
	JCheckBox jcbAgree;
	
	public CancelMembershipView(JPanel parentPanel, CardLayout cardLayout) {
		JPanel panelCancelMenbership = new JPanel();
		panelCancelMenbership.setLayout(null);
		
		ImageIcon back = new ImageIcon("image/Cancel/memberCancel.png");
		JLabel jl_back = new JLabel(back);
		jl_back.setBounds(600, 0, 650, 700);
		
		
		jcbAgree = new JCheckBox("동의합니다.");
		Font font = new Font(jcbAgree.getFont().getName(), Font.BOLD, 18);
		jcbAgree.setFont(font);
		JButton btncancel = new JButton("회원탈퇴");
		setButtonFont(btncancel);
		btncancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jcbAgree.isSelected()) {
					// 회원탈퇴
					UserDao userDao = UserDao.getInstance();
					userDao.userDelete(MainView.getLoginUser());
					
					JOptionPane.showMessageDialog(null, "회원 탈퇴에 성공했습니다.", "회원탈퇴", JOptionPane.INFORMATION_MESSAGE);
					// 로그아웃
					MainView.setLoginUser(null);
					cardLayout.show(parentPanel, "LoginView");
					MainBottomView.loginView.show();
				} else {
					JOptionPane.showMessageDialog(null, "회원 탈퇴에 대한 동의가 필요합니다.", "회원탈퇴", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		jcbAgree.setBounds(750, 700, 150, 40);
		btncancel.setBounds(930, 700, 150, 40);

		panelCancelMenbership.add(jcbAgree);
		panelCancelMenbership.add(btncancel);
		panelCancelMenbership.add(jl_back);
		
		parentPanel.add(panelCancelMenbership, "CancelMembershipView");
	}
	
	public void show() {
		jcbAgree.setSelected(false);
	}
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
}
