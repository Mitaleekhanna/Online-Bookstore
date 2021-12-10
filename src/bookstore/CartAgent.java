package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import net.proteanit.sql.DbUtils;
import jade.lang.acl.ACLMessage;

     				
 public class CartAgent extends MainAgent
 {   static int len = 1;
 	 String userID;
 	 private TableModel cartItemsTable;
 	 private float totalAmount;	
	 public CartAgent() {
//		 userID = user_id;
		 System.out.println("in cartagent");
	 }  
     protected void setup() 
     {
    	System.out.println("cart-agent " + getAID().getName() + "is ready.");
    	Object[] args = getArguments();
    	userID = (String)args[0];
    	cartItemsTable = getCartItems(userID);
  		totalAmount = getCartTotal(userID);
    	 new Cart(this,userID,cartItemsTable,totalAmount);

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("cart-agent " + getAID().getName() + "is cycling.");
             }
         });
     } //  --- setup ---
     
//     public void updateCart() {
// 		cartItemsTable.setModel(shoppingAgent.getCartItems(userId));
// 		totalAmount.setText("Total amount payable: $"+String.valueOf(shoppingAgent.getCartTotal(userId)));
// 		if(shoppingAgent.getCartItems(userId).getRowCount()<=0) {
// 			payButton.setVisible(false);
// 		}else {
// 			payButton.setVisible(true);
// 		}
// 		
// 	}
    
     public TableModel getCartItems(String userId){
 		DBConnect db = new DBConnect();
     	ResultSet cartitems;
 		try {
 			cartitems = db.getCartItems(userId);
 			return DbUtils.resultSetToTableModel(cartitems);
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			return DbUtils.resultSetToTableModel(null);
 		}
     }
 	 public float getCartTotal(String userId) {
//         	SELECT *,SUM(quantity) as totalQuantity FROM `cart`  where user_id= GROUP by `isbn`
     		DBConnect db = new DBConnect();
         	ResultSet cartitems;
     		try {
     			return db.getCartTotal(userId);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			return 0;
     		}
         }
		   public boolean addBookToCart(String user_id, String ISBN) {
		 	DBConnect db = new DBConnect();
		 	boolean status;
				try {
					status = db.addBookToCart(user_id,ISBN);
					return status;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		     
		 }




 }  