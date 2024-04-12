package user.mypage;


import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.UserDao;
import dto.User;
import main.MainView;

public class MemberInfoEditView {
	MouseListener mouseListener;
	Boolean checkUserId;
	JTextField[] textField;
	
	public MemberInfoEditView (JPanel parentPanel) {
		checkUserId = false;
		JPanel memberInfoEditView = new JPanel();
		memberInfoEditView.setLayout(null);
		
		String[] buttonName = {"아이디 중복 확인", "회원정보 수정"};
		JButton[] button = new JButton[2];
		for(int i = 0; i < buttonName.length; i++) {
			button[i] = new JButton(buttonName[i]);
			memberInfoEditView.add(button[i]);
			setButtonFont(button[i]);
		}
		
		button[0].setBounds(1040, 200, 200, 40);
		button[1].setBounds(850, 500, 160, 60);
		
		String[] labelName = {"이름", "아이디", "비밀번호", "생년월일", "연락처", "주소"};
		JLabel[] label = new JLabel[6];
		for(int i = 0; i < labelName.length; i++) {
			label[i] = new JLabel(labelName[i]);
			memberInfoEditView.add(label[i]);
			setLabelFont(label[i]);
		}
		
		label[0].setBounds(740, 150, 200, 40);
		label[1].setBounds(740, 200, 200, 40);
		label[2].setBounds(740, 250, 200, 40);
		label[3].setBounds(740, 300, 200, 40);
		label[4].setBounds(740, 350, 200, 40);
		label[5].setBounds(740, 400, 200, 40);
	
		textField = new JTextField[6];
		for(int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			memberInfoEditView.add(textField[i]);
		}
		
		textField[0].setBounds(820, 150, 200, 40);
		textField[1].setBounds(820, 200, 200, 40);
		textField[2].setBounds(820, 250, 200, 40);
		textField[3].setBounds(820, 300, 200, 40);
		textField[4].setBounds(820, 350, 300, 40);
		textField[5].setBounds(820, 400, 400, 40);
		
		memberInfoEditViewMouseListener(textField);
		
		// 마우스리스너 연결
		for(int i = 0; i < buttonName.length; i++) {
			button[i].addMouseListener(mouseListener);
		}
		
		parentPanel.add(memberInfoEditView, "MemberInfoEditView");
	}
	
	public void show(User user) {
		checkUserId = false;
		for(int i = 0; i < textField.length; i++) {
			textField[i].setText("");;
		}
//		"이름", "아이디", "비밀번호", "생년월일", "연락처", "주소"
		textField[0].setText(user.userName);
		textField[1].setText(user.userId);
		textField[2].setText(user.userPw);
		textField[3].setText(Integer.toString(user.birthday));
		textField[4].setText(user.phoneNumber);
		textField[5].setText(user.address);
	}
	
	public void memberInfoEditViewMouseListener(JTextField[] textField) {
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
				case "아이디 중복 체크": {
					UserDao userDao = UserDao.getInstance();
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
				case "회원정보 수정": {
					// 아이디 중복 확인을 진행하지 않았을 경우
					if(checkUserId == false) {
						JOptionPane.showMessageDialog(null, "아이디 중복체크를 해주세요.", "아이디 중복 확인", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					try {
						User beforeUserInfo = MainView.getLoginUser();
						// "이름", "아이디", "비밀번호", "생년월일", "연락처", "주소"
						User insertUserInfo = new User(textField[1].getText(), textField[2].getText(), textField[0].getText(),
								Integer.parseInt(textField[3].getText()), textField[5].getText(), textField[4].getText());
						
						UserDao userDao = UserDao.getInstance();
						boolean result = userDao.userUpate(beforeUserInfo, insertUserInfo);
						if(result) {
							JOptionPane.showMessageDialog(null, "회원정보 수정에 성공했습니다.", "회원정보 수정", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "회원정보 수정에 실패했습니다.\n등록정보를 확인하여 주세요.", "회원정보 수정", JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "회원정보 수정에 실패했습니다.\n등록정보를 확인하여 주세요.", "회원정보 수정", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
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
