package bookstore;
import java.sql.SQLException;
import java.util.Collections;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;

     				
 public class LibrarianAgent extends MainAgent
 {   static int len = 1;
	 public LibrarianAgent() {
		 System.out.println("in librarian agent");
	 }  
     protected void setup() 
     {
    	 System.out.println("librarian-agent " + getAID().getName() + "is ready.");
    	 new LibrarianHome(this);

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("Librarian-agent " + getAID().getName() + "is cycling.");
             }
         });
     }   //  --- setup ---
}  