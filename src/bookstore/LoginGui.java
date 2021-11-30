package bookstore;
import javax.swing.*;
import java.awt.event.*;

public class LoginGui extends JFrame{
	private static JLabel l1,l2;
	JFrame f;
	UserAgent UserAgent=new UserAgent();
	public LoginGui() {
		this.f=new JFrame("Login");//creating instance of JFrame  
		l1=new JLabel("Email");  //Create label email
	    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
	     
	    l2=new JLabel("Password");  //Create label Password
	    l2.setBounds(30,50, 100,30);    
	     
	    JTextField emailtext = new JTextField(); //Create text field for email
	    emailtext.setBounds(110, 15, 200, 30);
	         
	    JPasswordField F_pass=new JPasswordField(); //Create text field for password
	    F_pass.setBounds(110, 50, 200, 30);
	       
	    JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
	    login_but.setBounds(130,90,80,25);//Dimensions for button
	    login_but.addActionListener(new ActionListener() {  //Perform action
	         
	        public void actionPerformed(ActionEvent e){ 
	 
	        String email = emailtext.getText(); //Store email entered by the user in the variable "email"
	        String password = F_pass.getText(); //Store password entered by the user in the variable "password"
	         
	        if(email.equals("")) //If email is null
	        {
	            JOptionPane.showMessageDialog(null,"Please enter username"); //Display dialog box with the message
	        } 
	        else if(password.equals("")) //If password is null
	        {
	            JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
	        }
	        else {
	        try {
				UserAgent.loginuser(email, password);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        disposeframe();
	        }
	       }
	   });
	    f.add(F_pass); //add password
	    f.add(login_but);//adding button in JFrame  
	    f.add(emailtext);  //add email
	    f.add(l1);  // add label1 i.e. for email label
	    f.add(l2); // add label2 i.e. for password
	     
	    f.setSize(400,180);//400 width and 500 height  
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);//making the frame visible 
	    f.setLocationRelativeTo(null);
	}
    public void disposeframe() {
        this.f.dispose();
    }
    
    public static void main(String[] args) {
		System.out.println("hello from login class");
		new LoginGui();
	}

}
