package bookstore;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.util.HashSet;
import java.util.Set;

public class MainAgent extends Agent {
    protected Set<AID> searchService(String serviceName) {
        System.out.println("Main agent launched");
        Set<AID> agents = new HashSet<>();
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceName.toLowerCase());
        dfd.addServices(sd);

        SearchConstraints st = new SearchConstraints();
        st.setMaxResults(Long.valueOf(-1));

        try {
            DFAgentDescription[] results = DFService.search(this, dfd, st);
            for (DFAgentDescription result : results) {
                agents.add(result.getName());
            }
            return agents;
        } catch (FIPAException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected void deregister()  {
        System.out.println("Deregistering" + getLocalName());
        try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }


    protected void registerService(String serviceName) {
        DFAgentDescription ad = new DFAgentDescription();
        ad.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setName(getLocalName());
        sd.setType(serviceName.toLowerCase());
        ad.addServices(sd);
        try {
            DFService.register(this, ad);
           
        } catch (FIPAException ex) {
            ex.printStackTrace();
        }
    }
    
   
    protected void createAgent(String name, String className) {
        AID agentID = new AID(name, AID.ISLOCALNAME);
        AgentContainer controller = getContainerController();
        try {
            AgentController agent = controller.createNewAgent(name, className, null);
            agent.start();
            System.out.println("+++ Created: " + agentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void createAgentwithArgs(String name, String className,Object[] arg) {
        AID agentID = new AID(name, AID.ISLOCALNAME);
        AgentContainer controller = getContainerController();
        try {
            AgentController agent = controller.createNewAgent(name, className, arg);
            agent.start();
            System.out.println("+++ Created: " + agentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void killAgent(String name) {
        AID agentID = new AID(name, AID.ISLOCALNAME);
        AgentContainer controller = getContainerController();
        try {
            AgentController agent = controller.getAgent(name);
            agent.kill();
            System.out.println("+++ Killed: " + agentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}