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
 	 String userID;
 	 CustomerHome ch;
	 public CustomerAgent() {
//		 userID = user_id;
		 System.out.println("in customeragent");
	 }  
     protected void setup() 
     {
    	 System.out.println("Customer-agent " + getAID().getName() + "is ready.");
    	 registerService("book-buying");
    	 Object[] args = getArguments();
    	 userID = (String)args[0]; 
    	 ch = new CustomerHome(this,userID);

    	 addBehaviour(new CyclicBehaviour() {
             public void action() {
                 ACLMessage msg, reply = null;

                 msg = myAgent.receive();

                 if (msg != null) {
                	 String content = msg.getContent();
                	 System.out.print(content);
                	 if (msg.getConversationId() == Constants.ADD_BOOKS){
                		 ch.addBooktoListcustomer();
                	 }
                 }
               }
                 
     });
     }   //  --- setup ---
    



 }  