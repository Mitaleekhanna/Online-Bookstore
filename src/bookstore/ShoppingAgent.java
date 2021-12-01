package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ShoppingAgent extends Agent{
	protected void setup() 
    {
        addBehaviour(  // -------- Anonymous SimpleBehaviour 

            new SimpleBehaviour( this ) 
            {
            	public void action() 
                {
            		System.out.println( "Hello World! My name is " + myAgent.getLocalName() );
                }
            	public boolean done() {  
               	 try {
					killAgent(myAgent.getLocalName());
				} catch (ControllerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               	 return true;  
               	 }
            }
        );
     }
        
        public void killAgent(String name) throws ControllerException {
            AID agentID = new AID(name, AID.ISLOCALNAME);
            jade.wrapper.AgentContainer controller = getContainerController();
            AgentController agent = controller.getAgent(name);
            agent.kill();
            System.out.println("+++ Killed: " + agentID);
           
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
//        	SELECT *,SUM(cart.quantity) as totalQuantity ,(books.price*SUM(cart.quantity)) as totalPrice FROM `cart`  INNER join books on books.isbn=cart.isbn  where cart.user_id= GROUP by cart.`isbn`	
        }
        public float getCartTotal(String userId) {
//        	SELECT *,SUM(quantity) as totalQuantity FROM `cart`  where user_id= GROUP by `isbn`
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