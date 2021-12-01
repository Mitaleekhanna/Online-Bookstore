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

public class Home extends JFrame {

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
	Home(String userType){
		this();
		this.userType=userType;
		switch (this.userType) {
		case "librarian":
			
			break;

		default:
			AddBooks.setVisible(false);
			break;
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel container = new JPanel();

		container.setLayout(new CardLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		container.add(searchPanel, "searchPanel");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		searchPanel.add(panel);
		
		JLabel searchLabel = new JLabel("Enter keyword");
		panel.add(searchLabel);
		
		searchKeyword = new JTextField();
		panel.add(searchKeyword);
		searchKeyword.setColumns(10);
		
		JButton searchButton = new JButton("Lookup");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(searchAgent.searchBooks(searchKeyword.getText()));
			}
		});
		panel.add(searchButton);
		
		JLabel results = new JLabel("Searched results");
		results.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchPanel.add(results);
		
		JScrollPane scrollPane = new JScrollPane();
		searchPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(searchAgent.getbooks());
		
		JPanel searchResultsPanel = new JPanel();
		searchPanel.add(searchResultsPanel);
		
		JPanel cartPanel = new JPanel();
		container.add(cartPanel, "cartPanel");
		
		JLabel cartItemsLabel = new JLabel("Cart items");
		cartPanel.add(cartItemsLabel);
		
		JPanel detailsPanel = new JPanel();
		container.add(detailsPanel, "detailsPanel");
		
		JLabel title = new JLabel("book title");
		detailsPanel.add(title);
		
		JPanel navbar = new JPanel();

		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "searchPanel");
			}
		});
		navbar.add(search);
		
		JButton cart = new JButton("Cart");
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "cartPanel");
			}
		});
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		navbar.add(cart);
		
		AddBooks = new JButton("AddBooks");
		AddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "librarianPanel");
			}
		});
		navbar.add(AddBooks);
		
		JButton Profile = new JButton("Profile");
		navbar.add(Profile);
		
		JButton Logout = new JButton("Logout");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		navbar.add(Logout);
		
		contentPane.add(navbar);
		
		contentPane.add(container);
		
		JPanel librarianPanel = new JPanel();
		container.add(librarianPanel, "librarianPanel");
		
		JLabel lblNewLabel = new JLabel("ISBN");
		
		ISBNText = new JTextField();
		ISBNText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		
		nameText = new JTextField();
		nameText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		
		authorText = new JTextField();
		authorText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Description");
		
		descriptionText = new JTextField();
		descriptionText.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Genere");
		
		genereText = new JTextField();
		genereText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Publication");
		
		publicationText = new JTextField();
		publicationText.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Quantity");
		
		quantityText = new JTextField();
		quantityText.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Price");
		
		priceText = new JTextField();
		priceText.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book newBook=new Book();
				newBook.setAuthor(authorText.getText());
				newBook.setDescription(descriptionText.getText());
				newBook.setGenre(genereText.getText());
				newBook.setISBN(ISBNText.getText());
				newBook.setPrice(priceText.getText());
				newBook.setPublication(publicationText.getText());
				newBook.setQuantity(quantityText.getText());
				newBook.setTitle(nameText.getText());
				newBook.save();
				JOptionPane.showMessageDialog(null, "Book added sucessfully."); 
			}
		});
		GroupLayout gl_librarianPanel = new GroupLayout(librarianPanel);
		gl_librarianPanel.setHorizontalGroup(
			gl_librarianPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_librarianPanel.createSequentialGroup()
					.addGap(221)
					.addGroup(gl_librarianPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_librarianPanel.createSequentialGroup()
							.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_librarianPanel.createSequentialGroup()
							.addGroup(gl_librarianPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(priceText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(quantityText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_librarianPanel.createSequentialGroup()
									.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51))
								.addComponent(publicationText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addComponent(genereText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_librarianPanel.createSequentialGroup()
									.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51))
								.addComponent(descriptionText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addComponent(authorText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_librarianPanel.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51))
								.addComponent(nameText, Alignment.LEADING, 150, 150, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_librarianPanel.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51))
								.addGroup(Alignment.LEADING, gl_librarianPanel.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51))
								.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(ISBNText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
							.addGap(205))))
				.addGroup(Alignment.TRAILING, gl_librarianPanel.createSequentialGroup()
					.addGap(274)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(251))
		);
		gl_librarianPanel.setVerticalGroup(
			gl_librarianPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_librarianPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ISBNText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(descriptionText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(genereText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(publicationText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quantityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		librarianPanel.setLayout(gl_librarianPanel);
		
		
		JPanel NamePanel = new JPanel();
		
		JLabel nameLabel = new JLabel("n");

		
		
	}
}
