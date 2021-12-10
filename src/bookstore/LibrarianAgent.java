package bookstore;
import java.sql.SQLException;
import java.util.Collections;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

     				
 public class LibrarianAgent extends MainAgent
 {   LibrarianHome Lbhome;
 		
	 public LibrarianAgent() {
		 System.out.println("in librarian agent");
	 }  
     protected void setup() 
     {
    	 System.out.println("librarian-agent " + getAID().getName() + "is ready.");
    	 Lbhome = new LibrarianHome(this);

    	 addBehaviour(new CyclicBehaviour() {
             public void action() {
                 ACLMessage msg, reply = null;

                 msg = myAgent.receive();

                 if (msg != null) {
                	 String content = msg.getContent();
                	 System.out.print(content);
                	 if (msg.getConversationId() == Constants.ADD_BOOKS){
                		 Lbhome.addBooktoList();
                	 }
                 }
               }
                 
     }); //  --- setup ---
     }
 }