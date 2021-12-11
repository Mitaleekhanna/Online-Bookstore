package bookstore;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;

     				
 public class UserManagerAgent extends MainAgent
 {  
	 public UserManagerAgent() {
		 
		 System.out.println("in usermanageragent");
	 }  
     protected void setup() 
     {
    	 System.out.println("UserManagers-agent " + getAID().getName() + "is ready.");
         Login user = new Login(this);

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("UserManagers-agent " + getAID().getName() + "is cycling.");
             }
         });
     }   //  --- setup ---
    
     public boolean registerUser(String userName, String password, String userrole, String email, String phone, String address) {
         	 DBConnect db = new DBConnect();
         	 int status = db.adduserdata(userName, password,userrole,email,phone,address);
         	 if(status == 1) {
         		System.out.println(userName+" Registered Successfully.");
         		return true;
         	 }else {
         		System.out.println("Some error occured"); 
         		return false;
         	 }
//             System.out.println(userName);
//             System.out.println(password);
     }
     public void loginuser(String email,String password) throws Exception {
    	 DBConnect db = new DBConnect();
     	 Map<String, String> user_data = db.loginuser(email, password);
     	 if(user_data != null) {
     		System.out.print(user_data.get("name"));
     		if(user_data.get("user_role").equals("customer")) {
//     			String userID = String.valueOf(user_data.get("user_id"));
     			 Object[] args = new Object[1];
     			 args[0] = user_data.get("user_id");
     			createAgentwithArgs("customer:"+user_data.get("name"), "bookstore.CustomerAgent",args);
     			
     			
     		}
     		else {
     			createAgent("librarian:"+user_data.get("name"), "bookstore.LibrarianAgent");
     		}
     		System.out.println(user_data.get("name") +" Logged in Successfully.");
     	}
     	 else {
     		JOptionPane.showMessageDialog(null, "Invalid Username or Password"); 
     	 }
}
     public String getUserRole(String email,String password) throws Exception {
    	 DBConnect db = new DBConnect();
     	 String status = db.getUserRole(email, password);
     	 if(status == "false") {
     		System.out.println("Some error occured");
     	}else {
     		System.out.println(status+" Registered Successfully.");
     	 }
     	 return status;
     }
     public  void sendmessage(String content, AID receiver, BookAddingAgent BookAddingAgent, String conversationId) {
		    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		    msg.setContent(content);
		    msg.setConversationId(conversationId);
		    msg.addReceiver(receiver);
		    BookAddingAgent.send(msg);
	 }
 }  