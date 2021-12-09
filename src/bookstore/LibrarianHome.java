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
import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Dimension;

public class LibrarianHome extends JFrame {
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
//	LibrarianHome(String userId,String userType){
//		this();
//		this.userId=userId;
//		updateCart();
//		this.userType=userType;
//		System.out.println(userId);
//		switch (this.userType) {
//		case "librarian":
//			
//			break;
//
//		default:
//			AddBooks.setVisible(false);
//			break;
//		}
//		repaint();
//	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Home frame = new Home();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LibrarianHome(LibrarianAgent LibrarianAgent) {
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                LibrarianAgent.killAgent(LibrarianAgent.getLocalName());
	            }
	        });
		 this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel container = new JPanel();

		container.setLayout(new CardLayout(0, 0));
		
		JPanel homepanel = new JPanel();
		container.add(homepanel, "homePanel");
		homepanel.setLayout(new BoxLayout(homepanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		homepanel.add(panel);
		
		JLabel results = new JLabel("All Books");
		results.setAlignmentX(Component.CENTER_ALIGNMENT);
		homepanel.add(results);
		
		JScrollPane scrollPane = new JScrollPane();
		homepanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				String ISBN = table.getModel().getValueAt(row, column).toString();
				populateDetailsPane(ISBN);
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "detailsPanel");
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(searchAgent.getbooks());
		
		JPanel searchResultsPanel = new JPanel();
		homepanel.add(searchResultsPanel);
		
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
		
		JPanel panel_1 = new JPanel();
		cartPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		totalAmount = new JLabel("New label");
		
		panel_1.add(totalAmount);
		
		payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "paymentPanel");
			}
		});
		panel_1.add(payButton);
		
		JPanel detailsPanel = new JPanel();
		container.add(detailsPanel, "detailsPanel");
		
		JLabel lblNewLabel_8 = new JLabel("Book Details");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_9 = new JLabel("ISBN");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10 = new JLabel("Title");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_9_1 = new JLabel("Author");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10_1 = new JLabel("Description");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_9_2 = new JLabel("Genre");
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10_2 = new JLabel("Publication");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_9_3 = new JLabel("Quantity");
		lblNewLabel_9_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10_3 = new JLabel("Price");
		lblNewLabel_10_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10_3_1 = new JLabel("Date added");
		lblNewLabel_10_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		detailsGenre = new JLabel("Genre");
		detailsGenre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsGenre.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsPublication = new JLabel("Publication");
		detailsPublication.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsPublication.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsQuantity = new JLabel("Quantity");
		detailsQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsPrice = new JLabel("Price");
		detailsPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsPrice.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsDateAdded = new JLabel("Date added");
		detailsDateAdded.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsDateAdded.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsTitle = new JLabel("Title");
		detailsTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsTitle.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsISBN = new JLabel("ISBN");
		detailsISBN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsISBN.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsAuthor = new JLabel("Author");
		detailsAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		
		detailsDescription = new JLabel("Description");
		detailsDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detailsDescription.setHorizontalAlignment(SwingConstants.LEFT);
		

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "homePanel");
			}
		});
		GroupLayout gl_detailsPanel = new GroupLayout(detailsPanel);
		gl_detailsPanel.setHorizontalGroup(
			gl_detailsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addGap(132)
					.addComponent(btnNewButton_1)
					.addGap(18)
//					.addComponent(addTocart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(230))
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_detailsPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_9_1, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
								.addComponent(lblNewLabel_10_1, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_9_2, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel_10_2, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_9_3, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_10_3, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_10_3_1, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(52)
					.addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(4)
							.addComponent(detailsISBN, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(detailsTitle, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(detailsAuthor, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(detailsDescription, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(41))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(4)
							.addComponent(detailsGenre, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(42))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(detailsPublication, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(42))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(detailsQuantity, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addGap(43))
						.addComponent(detailsDateAdded, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(detailsPrice, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
					.addGap(141))
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addGap(225)
					.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
					.addGap(204))
		);
		gl_detailsPanel.setVerticalGroup(
			gl_detailsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_8)
					.addGap(18)
					.addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_9)
							.addGap(17)
							.addComponent(lblNewLabel_10)
							.addGap(17)
							.addComponent(lblNewLabel_9_1)
							.addGap(17)
							.addComponent(lblNewLabel_10_1)
							.addGap(23)
							.addComponent(lblNewLabel_9_2)
							.addGap(18)
							.addComponent(lblNewLabel_10_2)
							.addGap(19)
							.addComponent(lblNewLabel_9_3)
							.addGap(17)
							.addComponent(lblNewLabel_10_3)
							.addGap(18)
							.addComponent(lblNewLabel_10_3_1))
						.addGroup(gl_detailsPanel.createSequentialGroup()
							.addComponent(detailsISBN)
							.addGap(17)
							.addComponent(detailsTitle)
							.addGap(17)
							.addComponent(detailsAuthor)
							.addGap(17)
							.addComponent(detailsDescription)
							.addGap(23)
							.addComponent(detailsGenre)
							.addGap(18)
							.addComponent(detailsPublication)
							.addGap(19)
							.addComponent(detailsQuantity)
							.addGap(17)
							.addComponent(detailsPrice)
							.addGap(18)
							.addComponent(detailsDateAdded)))
					.addGap(31)
					.addGroup(gl_detailsPanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(addTocart)
						.addComponent(btnNewButton_1))
					.addGap(165))
		);
		detailsPanel.setLayout(gl_detailsPanel);
		
		JPanel navbar = new JPanel();
		
		JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "homePanel");
			}
		});
		navbar.add(home);
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianAgent.createAgent("SearchAgent", "bookstore.SearchAgent");
			}
		});
		navbar.add(search);
		

		
		AddBooks = new JButton("AddBooks");
		AddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				CardLayout cl = (CardLayout)(container.getLayout());
//			    cl.show(container, "librarianPanel");
				LibrarianAgent.createAgent("AddBooks","bookstore.BookAddingAgent");
			}
		});
		navbar.add(AddBooks);
		

		JButton Logout = new JButton("Logout");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianAgent.killAgent(LibrarianAgent.getLocalName());
				LibrarianAgent.createAgent("UserManager1", "bookstore.UserManagerAgent");
				jFrame.dispose();
				}
		});
		navbar.add(Logout);
		
		contentPane.add(navbar);
		
		contentPane.add(container);
		

		
		JPanel paymentPanel = new JPanel();
		paymentPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		container.add(paymentPanel, "paymentPanel");

		JLabel lblChooseYourPayment = new JLabel("Choose your payment method");
		
		JButton creditButton = new JButton("Credit card");
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order placed successfully! reference #"+shoppingAgent.placeOrder(userId, "credit"));
				table.setModel(searchAgent.getbooks());
				cartItemsTable.setModel(shoppingAgent.getCartItems(userId));
				if(shoppingAgent.getCartItems(userId).getRowCount()<=0) {
					payButton.setVisible(false);
				}else {
					payButton.setVisible(true);
				}
			}
		});
		creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton debitButton = new JButton("Debit");
		debitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order placed successfully! reference #"+shoppingAgent.placeOrder(userId, "debit"));
				table.setModel(searchAgent.getbooks());
				updateCart();
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
		

		
		JPanel profilePanel = new JPanel();
		container.add(profilePanel, "profilePanel");
		
		orderSet = new JPanel();
		profilePanel.add(orderSet);
		orderSet.setLayout(new BoxLayout(orderSet, BoxLayout.Y_AXIS));
		
//		JPanel order = new JPanel();
//		orderSet.add(order);
//		order.setLayout(new BoxLayout(order, BoxLayout.Y_AXIS));
//		
//		JLabel orderId = new JLabel("order#:");
//		orderId.setHorizontalAlignment(SwingConstants.LEFT);
//		orderId.setHorizontalTextPosition(SwingConstants.LEFT);
//		orderId.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		order.add(orderId);
//		
//		JPanel Items = new JPanel();
//		order.add(Items);
//		
//		JLabel lblNewLabel_11 = new JLabel("Book1 qty:1 price:10");
//		Items.add(lblNewLabel_11);
		
		
		JPanel NamePanel = new JPanel();
		
		JLabel nameLabel = new JLabel("n");
		this.jFrame.add(contentPane);
		this.jFrame.setVisible(true);
		
		
	}
	
	  public void addBooktoList() {
		  table.setModel(searchAgent.getbooks());
	    }

	public void populateDetailsPane(String ISBN) {
		Book book=searchAgent.getBookFromISBN(ISBN);
		detailsAuthor.setText(book.getAuthor());
		detailsGenre.setText(book.getGenre());
		detailsISBN.setText(book.getISBN());
		detailsTitle.setText(book.getTitle());
		detailsDescription.setText(book.getDescription());
		detailsPublication.setText(book.getPublication());
		detailsQuantity.setText(book.getQuantity());
		detailsPrice.setText(book.getPrice());
		detailsDateAdded.setText(book.getDateAdded());
		
	}
	
	public void updateCart() {
		cartItemsTable.setModel(shoppingAgent.getCartItems(userId));
		totalAmount.setText("Total amount payable: $"+String.valueOf(shoppingAgent.getCartTotal(userId)));
		if(shoppingAgent.getCartItems(userId).getRowCount()<=0) {
			payButton.setVisible(false);
		}else {
			payButton.setVisible(true);
		}
		
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
