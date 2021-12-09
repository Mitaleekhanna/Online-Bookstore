package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;

public class BookAddingAgent extends MainAgent{
	
	 public BookAddingAgent() {
		 System.out.println("in bookadding Agent");
	 }  
     
	 protected void setup() 
     {
    	 System.out.println("bookadding-agent " + getAID().getName() + "is ready.");
    	 new BookAdding(this);
    	 addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("Bookadding-agent " + getAID().getName() + "is cycling.");
             }
         });
     } 
	 
	 public  void sendmessage(String content, AID receiver, BookAddingAgent BookAddingAgent, String conversationId) {
		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		    msg.setContent(content);
		    msg.setConversationId(conversationId);
		    msg.addReceiver(receiver);
		    BookAddingAgent.send(msg);
	 }
	 
	

}