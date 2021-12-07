package bookstore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterGui extends JFrame{
	
	private static JLabel password1, label, userrolelabel,email,phone,address;
	private static JTextField username,emailtext,phonetext,addresstext;
	private static JButton button;
	private static JPasswordField Password;
	CustomerAgent UserAgent=new CustomerAgent();
	JFrame frame;
	public RegisterGui() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		// JFrame class
		this.frame = new JFrame();
		frame.setTitle("LOGIN PAGE");
		frame.setLocation(new Point(1200, 300));
		frame.add(panel);
		frame.setSize(new Dimension(1200, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Username label constructor
		label = new JLabel("Name");
		label.setBounds(100, 8, 70, 20);
		panel.add(label);
		// Username TextField constructor
		username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		panel.add(username);
		// Password Label constructor
		password1 = new JLabel("Password");
		password1.setBounds(100, 55, 70, 20);
		panel.add(password1);
		// Password TextField
		Password = new JPasswordField();
		Password.setBounds(100, 75, 193, 28);
		panel.add(Password);
		//userrole
		userrolelabel = new JLabel("User Role");
		userrolelabel.setBounds(100, 105, 70, 20);
		panel.add(userrolelabel);
		//Userrole dropdown
		String[] userroles = {"admin","librarian","customer"};
        JComboBox comboBox_userroles = new JComboBox(userroles);
        comboBox_userroles.setBounds(100, 130, 193, 28);
        panel.add(comboBox_userroles);
        //email
        email = new JLabel("Email");
		email.setBounds(100, 160, 70, 20);
		panel.add(email);
		// email TextField
		emailtext = new JTextField();
		emailtext.setBounds(100, 185, 193, 28);
		panel.add(emailtext);
        //phone number
		phone = new JLabel("Phone Number");
		phone.setBounds(100,225, 70, 20);
		panel.add(phone);
		// phone number TextField
		phonetext = new JTextField();
		phonetext.setBounds(100,250, 193, 28);
		panel.add(phonetext);
		//Address number
		address = new JLabel("Address");
		address.setBounds(100,280, 70, 20);
		panel.add(address);
		// address TextField
		addresstext = new JTextField();
		addresstext.setBounds(100,315, 193, 28);
		panel.add(addresstext);
		// Button constructor
		button = new JButton("Register");
		button.setBounds(100,345, 90, 25);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
//		button.addActionListener(this);
		panel.add(button);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      // Adding the listeners to components..
	    button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Username = username.getText();
				String Password1 = Password.getText();
				String userrole = comboBox_userroles.getSelectedItem().toString();
				String email = emailtext.getText();
				String phone = phonetext.getText();
				String address = addresstext.getText();
				if (UserAgent != null) {
					System.out.println("in useragent");
					UserAgent.registerUser(Username, Password1,userrole,email,phone,address);
				}
				
		        System.out.println("User: " + Username + " is registered successfully");
		        disposeframe();
				
			}
	    	
	    });
	    add(panel, BorderLayout.CENTER);
	    setTitle("Please Register Here !");
	    setSize(450,350);
	    setVisible(true);
	}
	public static void main(String[] args) {
		System.out.println("hello");
		new RegisterGui();
	}
	
	public void disposeframe() {
        this.frame.dispose();
    }

}
