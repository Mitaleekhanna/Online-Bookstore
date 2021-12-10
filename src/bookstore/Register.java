package bookstore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register extends JFrame {
	
	JFrame jFrame;
	private JPanel panel_1;
	private JTextField name;
	private JLabel passwordLabel;
	private JComboBox roleSelector;
	private JLabel roleLabel;
	private JLabel emailLabel;
	private JTextField email;
	private JLabel phoneLabel;
	private JTextField phone;
	private JTextArea address;
	CustomerAgent UserAgent = new CustomerAgent();
	private JPasswordField password;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Register frame = new Register();
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
	public Register(UserManagerAgent UserManagerAgent) {
		this.jFrame = new JFrame();
		this.jFrame.setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_1);
		 this.jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                super.windowClosing(windowEvent);
	                
	            }
	        });
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(161, 53, 44, 13);
		
		name = new JTextField();
		name.setBounds(211, 50, 127, 19);
		name.setColumns(10);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBounds(141, 92, 64, 13);
		
		roleLabel = new JLabel("Role");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setBounds(141, 121, 65, 13);
		
		roleSelector = new JComboBox();
		roleSelector.setBounds(211, 118, 127, 19);
		roleSelector.setModel(new DefaultComboBoxModel(new String[] {"librarian", "customer"}));
		
		emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setBounds(141, 156, 66, 13);
		
		email = new JTextField();
		email.setBounds(211, 153, 127, 19);
		email.setColumns(10);
		
		phoneLabel = new JLabel("Phone number");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		phoneLabel.setBounds(115, 185, 91, 13);
		
		phone = new JTextField();
		phone.addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent ke) {
	            String value = phone.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	phone.setEditable(true);
	              
	            } else {
	            	phone.setEditable(false);
	              
	            }
			}
		});
		phone.setBounds(211, 182, 127, 19);
		phone.setColumns(10);
		panel_1.setLayout(null);
		panel_1.add(nameLabel);
		panel_1.add(name);
		panel_1.add(passwordLabel);
		panel_1.add(roleLabel);
		panel_1.add(phoneLabel);
		panel_1.add(emailLabel);
		panel_1.add(email);
		panel_1.add(roleSelector);
		panel_1.add(phone);
		
		address = new JTextArea();
		address.setBounds(211, 211, 127, 69);
		panel_1.add(address);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setBounds(141, 217, 65, 13);
		panel_1.add(addressLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = name.getText();
				String passwordText = password.getText();
				String userrole = roleSelector.getSelectedItem().toString();
				String emailText = email.getText();
				String phoneText = phone.getText();
				String addressText = address.getText();
				UserManagerAgent.registerUser(username, passwordText,userrole,emailText,phoneText,addressText);
//					JFrame home = new Home(roleSelector.getSelectedItem().toString());
//					JFrame login=new Login();
//					login.setVisible(true);
//					dispose(); 
				JOptionPane.showMessageDialog(null, "Registered Suceesfully");
				jFrame.dispose();
				
			}
		});
		registerButton.setBounds(187, 316, 85, 21);
		panel_1.add(registerButton);
		
		password = new JPasswordField();
		password.setBounds(211, 89, 127, 19);
		panel_1.add(password);
		this.jFrame.add(panel_1);
		this.jFrame.setVisible(true);
	}
//	boolean performRegistration() {
//		String username = name.getText();
//		String passwordText = password.getText();
//		String userrole = roleSelector.getSelectedItem().toString();
//		String emailText = email.getText();
//		String phoneText = phone.getText();
//		String addressText = address.getText();
//		if (UserAgent != null) {
//			System.out.println("in Re");
//			return 
//		}else {
//			return false;
//		}
//	}
}
