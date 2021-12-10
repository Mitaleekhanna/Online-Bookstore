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

public class Cart extends JFrame {
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
//	private SearchAgent searchAgent=new SearchAgent();
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



	public Cart(CartAgent CartAgent,String userId,TableModel tb,Float amount) {
//		updateCart();
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                CartAgent.killAgent(CartAgent.getLocalName());
	            }
	        });
		this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_1);
		JPanel container = new JPanel();
		container.setLayout(new CardLayout(0, 0));
		JPanel cartPanel = new JPanel();
		container.add(cartPanel, "cartPanel");
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		
		JLabel cartItemsLabel = new JLabel("Cart items");
		cartItemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cartItemsLabel.setAlignmentY(0.0f);
		cartPanel.add(cartItemsLabel);
		
		JPanel cartItemsPanel = new JPanel();
		cartItemsPanel.setMaximumSize(new Dimension(32767, 200));
		cartItemsPanel.setBounds(new Rectangle(0, 0, 0, 200));
		cartPanel.add(cartItemsPanel);
		cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		cartItemsPanel.add(scrollPane_1);
		
		cartItemsTable = new JTable();
		scrollPane_1.setViewportView(cartItemsTable);
//		w	System.out.print(tb);
		cartItemsTable.setModel(tb);
		System.out.print(amount);
		totalAmount = new JLabel("New label");
		totalAmount.setText("Total amount payable: $"+String.valueOf(amount));
		payButton = new JButton("Pay");
		if(CartAgent.getCartItems(userId).getRowCount()<=0) {
			payButton.setVisible(false);
		}else {
			payButton.setVisible(true);
		}
		
		
		cartPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.add(totalAmount);
		
		payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "paymentPanel");
			}
		});
		panel_1.add(payButton);
		
		JPanel paymentPanel = new JPanel();
		paymentPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		container.add(paymentPanel, "paymentPanel");

		JLabel lblChooseYourPayment = new JLabel("Choose your payment method");
		
		JButton creditButton = new JButton("Credit card");
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order placed successfully! reference #"+shoppingAgent.placeOrder(userId, "credit"));
				CartAgent.killAgent(CartAgent.getLocalName());
				Object[] args = new Object[1];
     			args[0] = userId;
//     			CartAgent.createAgentwithArgs("ShoppingAgent", "bookstore.ShoppingAgent",args);
//				table.setModel(searchAgent.getbooks());
//				cartItemsTable.setModel(shoppingAgent.getCartItems(userId));
//				if(CartAgent.getCartItems(userId).getRowCount()<=0) {
//					payButton.setVisible(false);
//				}else {
//					payButton.setVisible(true);
//				}
			}
		});
		creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton debitButton = new JButton("Debit");
		debitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order placed successfully! reference #"+shoppingAgent.placeOrder(userId, "debit"));
				CartAgent.killAgent(CartAgent.getLocalName());
//				table.setModel(searchAgent.getbooks());
//				updateCart();
			}
		});
		debitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout gl_paymentPanel = new GroupLayout(paymentPanel);
		gl_paymentPanel.setHorizontalGroup(
			gl_paymentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paymentPanel.createSequentialGroup()
					.addGap(217)
					.addComponent(lblChooseYourPayment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(222))
				.addGroup(Alignment.TRAILING, gl_paymentPanel.createSequentialGroup()
					.addGroup(gl_paymentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_paymentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(creditButton))
						.addGroup(gl_paymentPanel.createSequentialGroup()
							.addGap(246)
							.addComponent(debitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(245))
		);
		gl_paymentPanel.setVerticalGroup(
			gl_paymentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paymentPanel.createSequentialGroup()
					.addGap(7)
					.addComponent(lblChooseYourPayment)
					.addGap(18)
					.addComponent(creditButton)
					.addGap(18)
					.addComponent(debitButton)
					.addGap(466))
		);
		paymentPanel.setLayout(gl_paymentPanel);
		this.jFrame.add(container);
		this.jFrame.setVisible(true);
		JPanel NamePanel = new JPanel();
		JLabel nameLabel = new JLabel("n");

		
		
	}

//	public void populateDetailsPane(String ISBN) {
//		Book book=searchAgent.getBookFromISBN(ISBN);
//		detailsAuthor.setText(book.getAuthor());
//		detailsGenre.setText(book.getGenre());
//		detailsISBN.setText(book.getISBN());
//		detailsTitle.setText(book.getTitle());
//		detailsDescription.setText(book.getDescription());
//		detailsPublication.setText(book.getPublication());
//		detailsQuantity.setText(book.getQuantity());
//		detailsPrice.setText(book.getPrice());
//		detailsDateAdded.setText(book.getDateAdded());
//		
//	}
	
//	public void updateCart() {
//		cartItemsTable.setModel(shoppingAgent.getCartItems(userId));
//		totalAmount.setText("Total amount payable: $"+String.valueOf(shoppingAgent.getCartTotal(userId)));
//		if(shoppingAgent.getCartItems(userId).getRowCount()<=0) {
//			payButton.setVisible(false);
//		}else {
//			payButton.setVisible(true);
//		}
//		
//	}
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
