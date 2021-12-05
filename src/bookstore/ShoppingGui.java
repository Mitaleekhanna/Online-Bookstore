package bookstore;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ShoppingGui extends JFrame{
	ShoppingAgent ShoppingAgent=new ShoppingAgent();
	public ShoppingGui()   {
		 //enter details
        JFrame g = new JFrame("Enter Details");
        //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create labels
        JLabel l1,l2,l3,l4;
        l1=new JLabel("Book ID");  // Label 1 for Book ID
        l1.setBounds(30,15, 100,30); 
         
         
        l2=new JLabel("User ID");  //Label 2 for user ID
        l2.setBounds(30,53, 100,30); 
         
        l3=new JLabel("Quantity");  //Label 3 for quantity
        l3.setBounds(30,90, 100,30); 
        
        l4=new JLabel("Payment Type");  //Label 3 for payment type
        l4.setBounds(30,130, 100,30); 
         
        JTextField F_bid = new JTextField();
        F_bid.setBounds(110, 15, 200, 30);
         
         
        JTextField F_uid=new JTextField();
        F_uid.setBounds(110, 53, 200, 30);
         
        JTextField F_quantity=new JTextField();
        F_quantity.setBounds(110, 90, 200, 30);
        
      //Userrole dropdown
		String[] payment_type = {"Cash","Credit card","Debit card"};
	    JComboBox comboBox_paymenttypes = new JComboBox(payment_type);
	    comboBox_paymenttypes.setBounds(110, 130, 200, 30);
	    
         
        JButton create_but=new JButton("Submit");//creating instance of JButton  
        create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
        create_but.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e){
             
            int uid = Integer.parseInt(F_uid.getText());
            int isbn = Integer.parseInt(F_bid.getText());
            int quantity = Integer.parseInt(F_quantity.getText());
            String payment_type= comboBox_paymenttypes.getSelectedItem().toString();
            try {
				ShoppingAgent.addorder(uid,isbn,quantity,payment_type);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            }
        });
        g.add(l1);
        g.add(l2);
        g.add(l3);
        g.add(l4);
        g.add(F_bid);
        g.add(F_uid);
        g.add(F_quantity);
        g.add(comboBox_paymenttypes);
        g.add(create_but);
        g.setSize(350,200);//400 width and 500 height  
        g.setLayout(null);//using no layout managers  
        g.setVisible(true);//making the frame visible 
        g.setLocationRelativeTo(null);
		}
	public static void main(String[] args) {
		System.out.println("hello from shopping class");
		new ShoppingGui();
	}
}