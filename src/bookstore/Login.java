package bookstore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class Login extends JFrame {

	private JPanel panel1;
	private JTextField username;
	UserManagerAgent UserManagerAgent = new UserManagerAgent();
	private JLabel registerLabel;
	private JPasswordField password;
	JFrame jFrame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
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
	public Login(UserManagerAgent UserManagerAgent) {
		System.out.print("in login page");
		this.jFrame = new JFrame();
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                UserManagerAgent.killAgent(UserManagerAgent.getLocalName());
	            }
	        });
		 this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 123, 52, 96, 0 };
		gbl_contentPane.rowHeights = new int[] { 76, 19, 19, 21, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel1.setLayout(gbl_contentPane);

		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 1;
		panel1.add(usernameLabel, gbc_usernameLabel);

		username = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.anchor = GridBagConstraints.NORTHWEST;
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.gridx = 2;
		gbc_username.gridy = 1;
		panel1.add(username, gbc_username);
		username.setColumns(10);

		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 2;
		panel1.add(passwordLabel, gbc_passwordLabel);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validCredentials(username.getText(), password.getText()).equals("false")) {
					try {
						UserManagerAgent.loginuser(username.getText(), password.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					JFrame home = new Home(validCredentials(username.getText(), password.getText()),getUserRole(username.getText(), password.getText()));
//					home.setVisible(true);
//					//create the new agent using main agent class
//					
//					dispose(); 
				}
			}
		});
		
		password = new JPasswordField();
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 0);
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 2;
		gbc_password.gridy = 2;
		panel1.add(password, gbc_password);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		panel1.add(btnNewButton, gbc_btnNewButton);
		
		/*
		 * registerLabel = new JLabel("Register"); registerLabel.addMouseListener(new
		 * MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { JFrame register = new
		 * Register(); register.setVisible(true); dispose(); } });
		 * registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 10)); GridBagConstraints
		 * gbc_registerLabel = new GridBagConstraints(); gbc_registerLabel.gridx = 2;
		 * gbc_registerLabel.gridy = 4; panel1.add(registerLabel, gbc_registerLabel);
		 */
//		getContentPane().add(panel1);
//		panel1.setVisible(true);
		this.jFrame.add(panel1);
		this.jFrame.setVisible(true);
	}

	String validCredentials(String email, String password) {
		//remove this line
//		return true;
		if (email.equals("")) // If email is null
		{
			JOptionPane.showMessageDialog(null, "Please enter username"); // Display dialog box with the message
			return "false";
		} else if (password.equals("")) // If password is null
		{
			JOptionPane.showMessageDialog(null, "Please enter password"); // Display dialog box with the message
			return "false";
		} else {
			return "true";
//			try {
//				String status = UserManagerAgent.loginuser(email, password);
//				return status;
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				return "false";
//			}
		}
	}
	String getUserRole(String email, String password) {
		try {
			return UserManagerAgent.getUserRole(email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	String getUserId(String email, String password) {
		try {
			return UserManagerAgent.getUserRole(email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}



}
