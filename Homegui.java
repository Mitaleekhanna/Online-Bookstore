import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Homegui {

	private JFrame frame;
	
	Connection connection = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homegui window = new Homegui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Homegui() {
		initialize();
		connection=sqlconnection.dbconnector();
		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSearh = new JButton("Search for Books");
		btnSearh.setBounds(23, 52, 385, 23);
		frame.getContentPane().add(btnSearh);
		
		JButton btnViewBooks = new JButton("View Books");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Books Available"); 
				
				 Connection connection = dbconnector();
	            String sql="select * from books"; 
	            try {
	                Statement stmt = connection.createStatement(); 
	                stmt.executeUpdate("USE books"); 
	                stmt=connection.createStatement();
	                ResultSet rs=stmt.executeQuery(sql);
	                
	                JTable book_list= new JTable(); 
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
	 
	                f.getContentPane().add(scrollPane); 
	                f.setSize(800, 400); 
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
                f.setSize(800, 400); 
                f.setVisible(true);
                f.setLocationRelativeTo(null);
		
		
			}

			
		});
		
		btnViewBooks.setBackground(new Color(102, 153, 204));
		btnViewBooks.setBounds(23, 123, 156, 23);
		frame.getContentPane().add(btnViewBooks);
		
		JButton btnNewButton = new JButton("Ordered Books");
		btnNewButton.setBackground(new Color(102, 153, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame f = new JFrame("Previously Ordered");
               
                 
                //Connection connection = dbconnector();
                //String sql="select * from Ordered";
               // try {
                    
                    //ResultSet rs=stmt.executeQuery(sql);
                  //  JTable book_list= new JTable();
                   // book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                     
                   // JScrollPane scrollPane = new JScrollPane(book_list);
 
                    //f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                //} catch (SQLException e1) {
                    // TODO Auto-generated catch block
                 //    JOptionPane.showMessageDialog(null, e1);
               // }       
			}
		});
		btnNewButton.setBounds(23, 200, 156, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New Releases");
		btnNewButton_1.setBackground(new Color(102, 153, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new JFrame("Recent releases"); 
				
				 Connection connection = dbconnector();
	            String sql="select * from newreleases"; 
	            try {
	                Statement stmt = connection.createStatement(); 
	                stmt.executeUpdate("USE books"); 
	                stmt=connection.createStatement();
	                ResultSet rs=stmt.executeQuery(sql);
	                
	                JTable book_list= new JTable(); 
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
	 
	                f.getContentPane().add(scrollPane); 
	                f.setSize(800, 400); 
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
               f.setSize(800, 400); 
               f.setVisible(true);
               f.setLocationRelativeTo(null);
		
			}
		});
		btnNewButton_1.setBounds(252, 123, 156, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnBestseller = new JButton("Bestsellers");
		btnBestseller.setBackground(new Color(102, 153, 204));
		btnBestseller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new JFrame(" The Bestsellers"); 
				
				 Connection connection = dbconnector();
	            String sql="select * from books"; 
	            try {
	                Statement stmt = connection.createStatement(); 
	                stmt.executeUpdate("USE bestbooks"); 
	                stmt=connection.createStatement();
	                ResultSet rs=stmt.executeQuery(sql);
	                
	                JTable book_list= new JTable(); 
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
	 
	                f.getContentPane().add(scrollPane); 
	                f.setSize(800, 400); 
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
               f.setSize(800, 400); 
               f.setVisible(true);
               f.setLocationRelativeTo(null);
		
			}
		});
		btnBestseller.setBounds(252, 200, 156, 23);
		frame.getContentPane().add(btnBestseller);
		
		JLabel lblHome = new JLabel("     HOME");
		lblHome.setBackground(new Color(0, 51, 102));
		lblHome.setBounds(84, 11, 360, 14);
		frame.getContentPane().add(lblHome);
	}
}