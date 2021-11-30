package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ShoppingAgent extends Agent{
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
        
        public void buybook() {
        
        }
	
}