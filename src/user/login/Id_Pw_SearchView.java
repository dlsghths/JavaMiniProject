package user.login;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Desktop.Action;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDao;

public class Id_Pw_SearchView {
	MouseListener mouseListener;
	JTextField[] textField;
	
	public Id_Pw_SearchView(JPanel parentPanel) {
		JPanel panelSearchIdPw = new JPanel();
		panelSearchIdPw.setLayout(null);
		
		String[] labelName = {"이름", "연락처", "이름", "연락처", "아이디"};
		JLabel[] label = new JLabel[5];
		for(int i = 0; i < labelName.length; i++) {
			label[i] = new JLabel(labelName[i]);
			panelSearchIdPw.add(label[i]);
		}
		
		label[0].setBounds(360, 300, 200, 40);
		label[1].setBounds(350, 350, 200, 40);
		label[2].setBounds(1160, 280, 200, 40);
		label[3].setBounds(1150, 330, 200, 40);
		label[4].setBounds(1150, 380, 200, 40);
		
		textField = new JTextField[5];
		for(int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			panelSearchIdPw.add(textField[i]);
		}
		
		textField[0].setBounds(400, 300, 200, 40);
		textField[1].setBounds(400, 350, 200, 40);
		textField[2].setBounds(1200, 280, 200, 40);
		textField[3].setBounds(1200, 330, 200, 40);
		textField[4].setBounds(1200, 380, 200, 40);
	
		String[] buttonName = {"아이디 찾기", "비밀번호 찾기"};
		JButton[] button = new JButton[2];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			panelSearchIdPw.add(button[i]);
		}
		
		button[0].setBounds(620, 300, 100, 80);
		button[1].setBounds(1420, 300, 150, 80);
		
		searchViewMouseListener(textField);
		
		// 마우스리스너 연결
		for(int i = 0; i < buttonName.length; i++) {
			button[i].addMouseListener(mouseListener);
		}
		
		parentPanel.add(panelSearchIdPw, "idpwSearchView");
	}
	
	public void show() {
		for(int i = 0; i < textField.length; i++) {
			textField[i].setText("");
		}
	}
	
	public void searchViewMouseListener(JTextField[] textField) {
		mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				switch (button.getText()) {
				case "아이디 찾기": {
					UserDao userDao = UserDao.getInstance();
					String userId = userDao.searchUserId(textField[0].getText(), textField[1].getText());
					if(userId == null || userId == "") {
						// 사용자 아이디가 없을 경우
						JOptionPane.showMessageDialog(null, "일치하는 계정이 존재하지 않습니다.", "아이디 찾기", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String notice = String.format("%s 이름의 사용자 아이디는 %s 입니다.", textField[0].getText(), userId);
						JOptionPane.showMessageDialog(null, notice, "아이디 찾기", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				case "비밀번호 찾기": {
					UserDao userDao = UserDao.getInstance();				
					String userPw = userDao.searchUserPw(textField[4].getText(), textField[2].getText(), textField[3].getText());
					if(userPw == null || userPw == "") {
						// 사용자 정보가 없을 경우
						JOptionPane.showMessageDialog(null, "일치하는 계정이 존재하지 않습니다.", "비밀번호 찾기", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String notice = String.format("%s 계정의 사용자 비밀번호는 %s 입니다.", textField[2].getText(), userPw);
						JOptionPane.showMessageDialog(null, notice, "비밀번호 찾기", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				default:
					break;
				}
			}
		};	
	}
}
