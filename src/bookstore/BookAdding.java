package bookstore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.core.AID;

import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BookAdding extends JFrame {
	private JTextField ISBNText;
	private JTextField nameText;
	private JTextField authorText;
	private JTextField descriptionText;
	private JTextField genereText;
	private JTextField publicationText;
	private JTextField quantityText;
	private JTextField priceText;
	private JButton AddBooks;
	JFrame jFrame;
	public BookAdding(BookAddingAgent BookAddingAgent) {
		System.out.print("in Add books page");
		JPanel container = new JPanel();

		container.setLayout(new CardLayout(0, 0));
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                BookAddingAgent.killAgent(BookAddingAgent.getLocalName());
	            }
	        });
		 this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	
		container.setLayout(new CardLayout(0, 0));
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
				BookAddingAgent.sendmessage("New Book Added!!",new AID("Librarian:asd", AID.ISLOCALNAME),BookAddingAgent,Constants.ADD_BOOKS);
				Set<AID> Aids = BookAddingAgent.searchService("book-buying");
				System.out.print(BookAddingAgent.searchService("book-buying"));
				for(AID aid: Aids) {
					BookAddingAgent.sendmessage("New Book Added!!",aid,BookAddingAgent,Constants.ADD_BOOKS);
					
				}
				jFrame.dispose();
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
		this.jFrame.add(librarianPanel);
		CardLayout cl = (CardLayout)(container.getLayout());
	    cl.show(container, "librarianPanel");
	    this.jFrame.setVisible(true);
	}
}
