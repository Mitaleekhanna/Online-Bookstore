package bookstore;
import java.sql.SQLException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;

     				
 public class UserAgent extends Agent 
 {   static int len = 1;
	 public UserAgent() {
		 System.out.println("in useragent");
	 }  
     protected void setup() 
     {
         addBehaviour(  // -------- Anonymous SimpleBehaviour 
 
             new SimpleBehaviour( this ) 
             {
                 int n=0;
                 
                 public void action() 
                 {
                     System.out.println( "Hello World! My name is " + 
                         myAgent.getLocalName() );
                     n++;
                 }
         
                 public boolean done() {  
                	 killAgent( myAgent.getLocalName());
                	 return true;  
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
     public void loginuser(String email,String password) throws Exception {
    	 DBConnect db = new DBConnect();
     	 String status = db.loginuser(email, password);
     	 if(status == "false") {
     		System.out.println("Some error occured");
     	}else {
     		System.out.println(status+" Registered Successfully.");
     	 }
     }
 }  