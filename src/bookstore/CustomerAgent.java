package bookstore;
import java.sql.SQLException;
import java.util.Collections;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;

     				
 public class CustomerAgent extends MainAgent
 {   static int len = 1;
	 public CustomerAgent() {
		 System.out.println("in customeragent");
	 }  
     protected void setup() 
     {
    	 System.out.println("Customer-agent " + getAID().getName() + "is ready.");
    	 new CustomerHome();

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("customer-agent " + getAID().getName() + "is cycling.");
             }
         });
     }   //  --- setup ---
    



 }  