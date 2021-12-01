package bookstore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
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

	private JPanel contentPane;
	private JTextField name;
	private JLabel passwordLabel;
	private JComboBox roleSelector;
	private JLabel roleLabel;
	private JLabel emailLabel;
	private JTextField email;
	private JLabel phoneLabel;
	private JTextField phone;
	private JTextArea address;
	UserAgent UserAgent = new UserAgent();
	private JPasswordField password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		roleSelector.setModel(new DefaultComboBoxModel(new String[] {"admin", "librarian", "customer"}));
		
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
		contentPane.setLayout(null);
		contentPane.add(nameLabel);
		contentPane.add(name);
		contentPane.add(passwordLabel);
		contentPane.add(roleLabel);
		contentPane.add(phoneLabel);
		contentPane.add(emailLabel);
		contentPane.add(email);
		contentPane.add(roleSelector);
		contentPane.add(phone);
		
		address = new JTextArea();
		address.setBounds(211, 211, 127, 69);
		contentPane.add(address);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setBounds(141, 217, 65, 13);
		contentPane.add(addressLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(performRegistration()) {
					JFrame home = new Home(roleSelector.getSelectedItem().toString());
					home.setVisible(true);
					dispose(); 
				}
			}
		});
		registerButton.setBounds(187, 316, 85, 21);
		contentPane.add(registerButton);
		
		password = new JPasswordField();
		password.setBounds(211, 89, 127, 19);
		contentPane.add(password);
	}
	boolean performRegistration() {
		String username = name.getText();
		String passwordText = password.getText();
		String userrole = roleSelector.getSelectedItem().toString();
		String emailText = email.getText();
		String phoneText = phone.getText();
		String addressText = address.getText();
		if (UserAgent != null) {
			System.out.println("in useragent");
			return UserAgent.registerUser(username, passwordText,userrole,emailText,phoneText,addressText);
		}else {
			return false;
		}
	}
}
