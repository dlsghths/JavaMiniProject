package admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import dao.ProductDao;
import dto.Product;
import main.listitem.PanelClothesListItem;

public class ProductManagementView {
	JPanel clothesListPanel;
	JScrollPane scrollPane;
	PanelClothesListItem panelClothesListItem;
	
	public ProductManagementView(JPanel parentPanel, JScrollPane scrollPane, CardLayout cardLayout) {
//		clothesListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
//		clothesListPanel.setBackground(Color.CYAN);
//		
//		ArrayList<Product> clothesList = new ArrayList<Product>();
//		clothesList = getClothesList();
//		panelClothesListItem = new PanelClothesListItem(clothesListPanel, parentPanel, clothesList, cardLayout);
//		
//		// 스크롤 기능 생성
//		scrollPane = new JScrollPane(clothesListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		int scrollHeight = (scrollPane.getPreferredSize().width / 1400);
//		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정
//		parentPanel.add("clothesList", scrollPane);
//		
//		clothesListPanel.setPreferredSize(new Dimension(500, scrollHeight * 420 + 20));
	}
	
	// 상품 리스트 목록 가져오기
	public ArrayList<Product> getClothesList() {
		ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = new ArrayList<Product>();
		productList = productDao.productSelect(null, null);
		
		return productList;
	}
	
	
	
//    ProductDao pd = new ProductDao();
//    ArrayList<Product> p_list = new ArrayList<>();
//    DefaultTableModel model;
//    JTable productM_table;
//    public String[] getproduct(Product p_list) {
//    	ImageIcon img = new ImageIcon(p_list.getProductImage());
//        String[] data = {Integer.toString(p_list.getProductNum()),p_list.getProductName(),p_list.getProductExplanation(),Integer.toString(p_list.getProductInventory())};
//        return data;
//    }
//    public JPanel ProductM_view() {
//        JPanel Mangement_Mainpanel = new JPanel(new BorderLayout());
//        JLabel product_label = new JLabel("상품목록");
//        String[] productM_title = {"상품 번호","상품명","상품 설명", "재고량"};
//        p_list = pd.productSelect(null, null);
//        String[][] productM_table_info = new String[p_list.size()][4];
//        
//        for(int i = 0 ; i < productM_table_info.length; i++) {
//        	productM_table_info[i] = getproduct(p_list.get(i));
//        }
//        
//        productM_table = new JTable(productM_table_info,productM_title);
//        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
//		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
//		JScrollPane productM_js = new JScrollPane(productM_table, v, h);
//		
//		Mangement_Mainpanel.add("West",product_label);
//		Mangement_Mainpanel.add(productM_js);
//		Mangement_Mainpanel.setBounds(0,50,1000,400);
//        return Mangement_Mainpanel;
//    }
}
