package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ShoppingAgent extends Agent{
	AID id = new AID("agent3", AID.ISLOCALNAME);
	 public ShoppingAgent() {
		 System.out.println("in shopping agent");
	 }  
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
    
    public void addorder(int uid,int bid,int quantity,String payment_type) throws SQLException {
    	 DBConnect db = new DBConnect();
     	 String book_name = db.addorder(uid,bid,quantity);
     	 int order_id = db.getlastorderid();
     	 int amount = db.getamountbyisbn(bid);
     	 PaymentAgent payment = new PaymentAgent();
     	 payment.addpayment(order_id, amount, payment_type);
     	 sendMessage(book_name);
     	 System.out.println(book_name +"added to orders");
     	 
    }
    
    public void sendMessage(String messageText) {
    	ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(id);
        send(message);
    }
    
//        public static void main(String[] args) throws SQLException {
//    		
//    		
//    	}
}