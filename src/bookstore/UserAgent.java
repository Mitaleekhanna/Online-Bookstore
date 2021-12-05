package bookstore;
import java.sql.SQLException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.lang.acl.ACLMessage;

     				
 public class UserAgent extends Agent 
 {   static int len = 1;
	 public UserAgent() {
		 System.out.println("in useragent");
	 }  
     protected void setup() 
     {
         addBehaviour(  // -------- Anonymous SimpleBehaviour 
 
        new CyclicBehaviour() 
             {
        	  
        	public void action() 
                 {
                	 ACLMessage msg = null;
               	  	 msg = myAgent.receive();
               	  	 if(msg != null) {
               	  		 System.out.print(msg);
               	  	 }
//                     System.out.println( "Hello World! My name is " + myAgent.getLocalName() );
                    
                 }
         
             }
         );
     }   //  --- setup ---
     public void killAgent(String name) {
         AID agentID = new AID(name, AID.ISLOCALNAME);
         jade.wrapper.AgentContainer controller = getContainerController();
         try {
             AgentController agent = controller.getAgent(name);
             agent.kill();
             System.out.println("+++ Killed: " + agentID);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     public void registerUser(String userName, String password, String userrole, String email, String phone, String address) {
         	 DBConnect db = new DBConnect();
         	 int status = db.adduserdata(userName, password,userrole,email,phone,address);
         	 if(status == 1) {
         		System.out.println(userName+" Registered Successfully.");
         	 }else {
         		System.out.println("Some error occured"); 
         	 }
//             System.out.println(userName);
//             System.out.println(password);
     }
     public String loginuser(String email,String password) throws Exception {
    	 DBConnect db = new DBConnect();
     	 String status = db.loginuser(email, password);
     	 if(status == "false") {
     		System.out.println("Some error occured");
     	}else {
     		System.out.println(status+" logged in Successfully.");
     	 }
     	 return status;
     }
     public void senduserid(String id, AID agentname) {
    	 ACLMessage message = new ACLMessage(ACLMessage.INFORM);
//         message.setConversationId(conversationId);
         message.setContent(id);
         AID agentid = new AID("YourAgentName", AID.ISLOCALNAME);
         message.addReceiver(agentname);
         send(message);
     }
     
 }  