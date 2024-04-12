package user.login;


import java.awt.CardLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;

import dao.ConnectDB;
import dao.UserDao;
import dto.User;
import main.MainBottomView;

public class JoinView {
	MouseListener mouseListener;
	Boolean checkUserId;
	JTextField[] textField;
	
	public JoinView(JPanel parentPanel, CardLayout cardLayout) {
		checkUserId = false;
		JPanel joinView = new JPanel();
		joinView.setLayout(null);
		
		String[] buttonName = {"아이디 중복 확인", "회원가입"};
		JButton[] button = new JButton[2];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			joinView.add(button[i]);
		}
		
		button[0].setBounds(1040, 200, 130, 40);
		button[1].setBounds(850, 500, 100, 60);
		
		String[] labelName = {"이름", "아이디", "비밀번호", "생년월일", "연락처", "주소"};
		JLabel[] label = new JLabel[6];
		for(int i = 0; i < labelName.length; i++) {
			label[i] = new JLabel(labelName[i]);
			joinView.add(label[i]);
		}
		
		label[0].setBounds(770, 150, 200, 40);
		label[1].setBounds(760, 200, 200, 40);
		label[2].setBounds(750, 250, 200, 40);
		label[3].setBounds(750, 300, 200, 40);
		label[4].setBounds(760, 350, 200, 40);
		label[5].setBounds(770, 400, 200, 40);
		
		textField = new JTextField[6];
		for(int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			joinView.add(textField[i]);
		}
		
		textField[0].setBounds(820, 150, 200, 40);
		textField[1].setBounds(820, 200, 200, 40);
		textField[2].setBounds(820, 250, 200, 40);
		textField[3].setBounds(820, 300, 200, 40);
		textField[4].setBounds(820, 350, 300, 40);
		textField[5].setBounds(820, 400, 400, 40);
		
		joinViewMouseListener(textField, cardLayout, parentPanel);
	
		// 마우스리스너 연결
		for(int i = 0; i < buttonName.length; i++) {
			button[i].addMouseListener(mouseListener);
		}
		
		parentPanel.add(joinView, "joinView");
	}
	
	public void show() {
		for(int i = 0; i < textField.length; i++) {
			textField[i].setText("");
		}
	}
	
	public void joinViewMouseListener(JTextField[] textField, CardLayout cardLayout, JPanel parentPanel) {
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
				case "아이디 중복 확인": {
					UserDao userDao = UserDao.getInstance();;
					boolean result = userDao.checkDuplication(textField[1].getText());
					if(result) {
						// 아이디 중복이 없음
						JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.", "아이디 중복 확인", JOptionPane.INFORMATION_MESSAGE);
						checkUserId = true;
					} else {
						// 아이디 중복이 있음
						JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.", "아이디 중복 확인", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				case  "인증하기": {

					break;
				}
				case "회원가입": {
					// 아이디 중복 확인을 진행하지 않았을 경우
					if(checkUserId == false) {
						JOptionPane.showMessageDialog(null, "아이디 중복체크를 해주세요.", "아이디 중복 확인", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					// "이름", "아이디", "비밀번호", "생년월일", "연락처", "주소"
					try {
						User user = new User(textField[1].getText(), textField[2].getText(), textField[0].getText(),
								Integer.parseInt(textField[3].getText()), textField[5].getText(), textField[4].getText());
						
						UserDao userDao = UserDao.getInstance();
						boolean insertCheck = userDao.userInsert(user);
						JOptionPane.showMessageDialog(null, "사용자 등록에 성공했습니다.", "회원가입", JOptionPane.INFORMATION_MESSAGE);
						
						cardLayout.show(parentPanel, "clothesList");
						MainBottomView.show(cardLayout);
						break;
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "사용자 등록에 실패하였습니다.\n등록정보를 확인하여 주세요.", "회원가입", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				default:
					break;
				}
			}
		};	
	}
}
