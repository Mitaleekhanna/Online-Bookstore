package bookstore;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ShoppingAgent extends MainAgent{
	String userID;
    protected void setup() 
    {
   	System.out.println("Shopping-agent " + getAID().getName() + "is ready.");
   	Object[] args = getArguments();
   	userID = (String)args[0];
   	new Shopping(this,userID);

        addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
            protected void onTick() {
                System.out.println("Shopping-agent " + getAID().getName() + "is cycling.");
            }
        });
    } //  --- setup ---
    
        
       
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

    public void addOrder(JPanel orderSet,Order orderObj) {
		JPanel order = new JPanel();
		orderSet.add(order);
		order.setLayout(new BoxLayout(order, BoxLayout.Y_AXIS));
		
		JLabel orderId = new JLabel("order#:"+orderObj.orderId);
		orderId.setHorizontalAlignment(SwingConstants.LEFT);
		orderId.setHorizontalTextPosition(SwingConstants.LEFT);
		orderId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		order.add(orderId);
		
		JPanel Items = new JPanel();
		order.add(Items);
		for (OrderItem ot:orderObj.orderitems) {
			Items.add(new JLabel(ot.name+" qty:"+ot.qty+" price:"+ot.price));
		}

	}
	public void updateProfile(JPanel orderSet) {
		orderSet.removeAll();
		for(Order order:getOrders(userID)) {
			addOrder(orderSet,order);
		}
	}

    
    //        public TableModel getCartItems(String userId){
//    		DBConnect db = new DBConnect();
//        	ResultSet cartitems;
//    		try {
//    			cartitems = db.getCartItems(userId);
//    			return DbUtils.resultSetToTableModel(cartitems);
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    			return DbUtils.resultSetToTableModel(null);
//    		}
//        	SELECT *,SUM(cart.quantity) as totalQuantity ,(books.price*SUM(cart.quantity)) as totalPrice FROM `cart`  INNER join books on books.isbn=cart.isbn  where cart.user_id= GROUP by cart.`isbn`	
//        }
//        public float getCartTotal(String userId) {
////        	SELECT *,SUM(quantity) as totalQuantity FROM `cart`  where user_id= GROUP by `isbn`
//    		DBConnect db = new DBConnect();
//        	ResultSet cartitems;
//    		try {
//    			return db.getCartTotal(userId);
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    			return 0;
//    		}
//        }
        
        public int placeOrder(String userId,String type) {
//        	SELECT *,SUM(quantity) as totalQuantity FROM `cart`  where user_id= GROUP by `isbn`
    		DBConnect db = new DBConnect();
        	ResultSet cartitems;
        	int paymentId;
    		try {
    			return db.payAmount(userId,type);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return 0;
    		}
        }
        
        public ArrayList<Order> getOrders(String userId) {
//        	SELECT *,SUM(quantity) as totalQuantity FROM `cart`  where user_id= GROUP by `isbn`
    		DBConnect db = new DBConnect();
        	ResultSet cartitems;
        	int paymentId;
    		try {
    			return db.getOrders(userId);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return new ArrayList<Order>();
    		}
        }
        
        
	
}