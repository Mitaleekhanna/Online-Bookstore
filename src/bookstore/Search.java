package bookstore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Search extends JFrame {

	private JPanel panel1;
	private JPanel contentPane;
	private JTable table;
	private JTextField searchKeyword;
	private JTextField username;
	UserManagerAgent UserManagerAgent = new UserManagerAgent();
	private JLabel registerLabel;
	private JPasswordField password;
	JFrame jFrame;

	public Search(SearchAgent SearchAgent) {
		System.out.print("in Search page");
		JPanel container = new JPanel();

		container.setLayout(new CardLayout(0, 0));
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                SearchAgent.killAgent(SearchAgent.getLocalName());
	            }
	        });
		 this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	

		
		JPanel searchPanel = new JPanel();
		
		
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
				table.setModel(SearchAgent.searchBooks(searchKeyword.getText()));
			}
		});
		panel.add(searchButton);
		
		JLabel results = new JLabel("Searched results");
		results.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchPanel.add(results);
		
		JScrollPane scrollPane = new JScrollPane();
		searchPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				String ISBN = table.getModel().getValueAt(row, column).toString();
				SearchAgent.populateDetailsPane(ISBN,SearchAgent);
				CardLayout cl = (CardLayout)(container.getLayout());
			    cl.show(container, "detailsPanel");
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(SearchAgent.getbooks());
		
		JPanel searchResultsPanel = new JPanel();
		searchPanel.add(searchResultsPanel);
		this.jFrame.add(searchPanel);
		CardLayout cl = (CardLayout)(container.getLayout());
	    cl.show(container, "searchPanel");
		
		this.jFrame.setVisible(true);
	}
	
	

	
	

}
