package user.login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

import dao.LoginDao;
import dto.User;
import main.MainBottomView;
import main.MainView;

public class LoginView {
	MouseListener mouseListener;
	JTextField[] textField;
	
	Id_Pw_SearchView idpwSearchView;
	JoinView joinView;
	
	public LoginView(JPanel parentPanel, CardLayout cardLayout) {
		JPanel panelLogin = new JPanel();
		panelLogin.setLayout(null);
		panelLogin.setBackground(null);
	
		String[] buttonName = {"로그인", "아이디 / 비밀번호 찾기", "회원가입"};
		JButton[] button = new JButton[3];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			setButtonFont(button[i]);
			panelLogin.add(button[i]);
		}
		button[0].setBounds(1040, 270, 100, 90);
		button[1].setBounds(830, 380, 320, 40);
		button[2].setBounds(830, 430, 320, 40);
		
		
		String[] labelName = {"아이디", "비밀번호"};
		JLabel[] label = new JLabel[2];
		for(int i = 0; i < labelName.length; i++) {
			label[i] = new JLabel(labelName[i]);
			setLabelFont(label[i]);
			panelLogin.add(label[i]);
		}
		
		label[0].setBounds(700, 280, 100, 20);
		label[1].setBounds(695, 330, 100, 20);
		
		
		textField = new JTextField[2];
		for(int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			panelLogin.add(textField[i]);
		}
		
		textField[0].setBounds(830, 270, 200, 40);
		textField[1].setBounds(830, 320, 200, 40);
		
		
		
		
		LoginViewMouseListener(textField, parentPanel, cardLayout);
		
		// 마우스리스너 연결
		for(int i = 0; i < buttonName.length; i++) {
			button[i].addMouseListener(mouseListener);
		}
		
		// 아이디/비밀번호 찾기 화면 연결
		idpwSearchView = new Id_Pw_SearchView(parentPanel);
		
		// 회원가입 화면 연결
		joinView = new JoinView(parentPanel, cardLayout);

		parentPanel.add(panelLogin, "LoginView");
	}
	
	public void show() {
		for(int i = 0; i < textField.length; i++) {
			textField[i].setText("");		
		}
	}
	
	public void LoginViewMouseListener(JTextField[] textField, JPanel parentPanel, CardLayout cardLayout) {
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
				switch (button.getText()){
				case "로그인": {
					LoginDao login = new LoginDao();
					User loginResult = login.requestLogin(textField[0].getText(), textField[1].getText());
					if(loginResult != null) {
						// 로그인 성공 처리
						MainView.setLoginUser(loginResult);
						cardLayout.show(parentPanel, "clothesList");
						MainBottomView.show(cardLayout);
					} else {
						// 로그인 실패 공지
						JOptionPane.showMessageDialog(null, "로그인 실패\n아이디 혹은 비밀번호가 일치하지 않습니다.", "로그인", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				case "아이디 / 비밀번호 찾기": {
					cardLayout.show(parentPanel, "idpwSearchView");
					idpwSearchView.show();
					break;
				}
				case "회원가입": {
					cardLayout.show(parentPanel, "joinView");
					joinView.show();
					break;
				}
				default:
					break;
				}
			}
		};
	}
	
	public void setButtonFont(JButton button) {
		Font font = new Font(button.getFont().getName(), Font.BOLD, 18);
		button.setFont(font);
	}
	
	public void setLabelFont(JLabel label) {
		Font font = new Font(label.getFont().getName(), Font.BOLD, 18);
		label.setFont(font);
	}
}
