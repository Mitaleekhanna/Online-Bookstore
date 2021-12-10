package bookstore;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class Shopping extends JFrame {
	//User details
	String userId;
	
	JFrame jFrame;
	private JPanel contentPane;
	private JTable table;
	private JTextField searchKeyword;
	private String userType;
	private JTextField textField;
	private JTextField ISBNText;
	private JTextField nameText;
	private JTextField authorText;
	private JTextField descriptionText;
	private JTextField genereText;
	private JTextField publicationText;
	private JTextField quantityText;
	private JTextField priceText;
	private JButton AddBooks;
	private SearchAgent searchAgent=new SearchAgent();
	private ShoppingAgent shoppingAgent=new ShoppingAgent();
	
	//deatils labels
	JLabel detailsGenre;
	JLabel detailsISBN;
	JLabel detailsTitle;
	JLabel detailsAuthor;
	JLabel detailsDescription;
	JLabel detailsPublication;
	JLabel detailsQuantity;
	JLabel detailsPrice;
	JLabel detailsDateAdded;
	private JTable table_1;
	
	//cart fields
	private JTable cartItemsTable;
	private JLabel totalAmount;
	
	//payment fields
	JButton payButton;
	
	//order fields
	JPanel orderSet;



	public Shopping(ShoppingAgent ShoppingAgent, String userId) {
//		updateCart();
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                ShoppingAgent.killAgent(ShoppingAgent.getLocalName());
	            }
	        });
		this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		JPanel container = new JPanel();
		container.setLayout(new CardLayout(0, 0));
		JPanel orderPanel = new JPanel();
		JPanel profilePanel = new JPanel();
		container.add(profilePanel, "profilePanel");
		orderSet = new JPanel();
		orderSet.removeAll();
		for(Order order:shoppingAgent.getOrders(userId)) {
			addOrder(orderSet,order);
		}
		profilePanel.add(orderSet);
		orderSet.setLayout(new BoxLayout(orderSet, BoxLayout.Y_AXIS));
		this.jFrame.add(container);
		this.jFrame.setVisible(true);
		
		
		
	}

	
	

	public void addOrder(JPanel orderSet,Order orderObj) {
		JPanel order = new JPanel();
		orderSet.add(order);
		order.setLayout(new BoxLayout(order, BoxLayout.Y_AXIS));
		
		JLabel orderId = new JLabel("order#:"+orderObj.orderId);
		orderId.setHorizontalAlignment(SwingConstants.LEFT);
		orderId.setHorizontalTextPosition(SwingConstants.LEFT);
		orderId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		order.add(orderId);
		
		JPanel Items = new JPanel();
		order.add(Items);
		for (OrderItem ot:orderObj.orderitems) {
			Items.add(new JLabel(ot.name+" qty:"+ot.qty+" price:"+ot.price));
		}

	}
	public void updateProfile(JPanel orderSet) {
		orderSet.removeAll();
		for(Order order:shoppingAgent.getOrders(userId)) {
			addOrder(orderSet,order);
		}
	}
}
