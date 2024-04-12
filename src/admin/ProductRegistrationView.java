package admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDao;
import dto.Product;
import main.MainBottomView;

public class ProductRegistrationView {
	JTextField[] textField;
	
	public ProductRegistrationView(JPanel parentPanel, CardLayout cardLayout) {
		JPanel panelRegistration = new JPanel(null);
		
		JButton button = new JButton("상품등록");
		button.setBounds(750, 450, 250, 50);
		panelRegistration.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.productNum = Integer.parseInt(extractNumber(textField[4].getText()));
				product.productName = textField[0].getText();
				product.productExplanation = textField[1].getText();
				product.productPrice = Integer.parseInt(textField[2].getText());
				product.productInventory = Integer.parseInt(textField[3].getText());
				product.productImage = textField[4].getText();
				
				
				ProductDao productDao = ProductDao.getInstance();
				productDao.productInsert(product);
				
				cardLayout.show(parentPanel, "clothesList");
				MainBottomView.show(cardLayout);
				JOptionPane.showMessageDialog(null, "상품정보를 등록하였습니다.", "상품등록", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		setButtonFont(button);
		
		// 상품번호 상품명 상품설명 상품가격 상품재고량 상품이미지경로
		String[] labelName = {"상품명", "상품 설명", "상품 가격", "상품 재고량"};
		JLabel[] label = new JLabel[4];
		for(int i = 0; i < labelName.length; i++) {
			label[i] = new JLabel(labelName[i]);
			panelRegistration.add(label[i]);
			setLabelFont(label[i]);
		}
		label[0].setBounds(650, 150, 200, 40);
		label[1].setBounds(650, 200, 200, 40);
		label[2].setBounds(650, 250, 200, 40);
		label[3].setBounds(650, 300, 200, 40);
		
		textField = new JTextField[5];
		for(int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			panelRegistration.add(textField[i]);
		}
		textField[0].setBounds(800, 150, 300, 40);
		textField[1].setBounds(800, 200, 300, 40);
		textField[2].setBounds(800, 250, 300, 40);
		textField[3].setBounds(800, 300, 300, 40);
		textField[4].setBounds(800, 350, 300, 40);
		
		JButton openButton = new JButton("Open Image");
		openButton.setBounds(620, 350, 160, 40);
		openButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField[4].setText(openFileChooser(panelRegistration));
			}
		});
		setButtonFont(openButton);
		panelRegistration.add(openButton);
		
		parentPanel.add(panelRegistration, "ProductRegistrationView");
	}
	
	public void show() {
		for(int i = 0; i < textField.length; i++) {
			textField[i].setText("");
		}
	}
	
	public String openFileChooser(JPanel frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file");

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            
            String pattern = ".*" + "WebMiniProject" + "(.*)"; // 특정 단어 이후의 내용을 추출하는 정규 표현식
            String extractedText = filePath.replaceAll(pattern, "$1");
            extractedText = extractedText.replaceAll("\\\\", "/");
            
            return extractedText.substring(1);
        }
        return "";
    }
	
	private static String extractNumber(String text) {
        // 숫자로 이루어진 부분을 추출하는 정규 표현식
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(); // 매칭되는 첫 번째 숫자 부분을 반환
        } else {
            return ""; // 매칭되는 숫자가 없을 경우 빈 문자열 반환
        }
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
