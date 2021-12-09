package bookstore;
import java.sql.SQLException;
import java.util.Collections;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;

     				
 public class CartAgent extends MainAgent
 {   static int len = 1;
 	 String userID;
	 public CartAgent() {
//		 userID = user_id;
		 System.out.println("in cartagent");
	 }  
     protected void setup() 
     {
    	 System.out.println("cart-agent " + getAID().getName() + "is ready.");
    	 Object[] args = getArguments();
    	 userID = (String)args[0]; 
    	 new Cart(this,userID);

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("cart-agent " + getAID().getName() + "is cycling.");
             }
         });
     } //  --- setup ---
    



 }  