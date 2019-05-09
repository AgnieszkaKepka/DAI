import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class Wykonawca extends Agent{
    protected void setup() {

        //Rejestracja usługi:
        // 1. Przygotowanie opisu agenta:
        DFAgentDescription dfd=new DFAgentDescription();
        dfd.setName(getAID());   //chcac sie zarejestrowac podaje aid
        ServiceDescription sd=new ServiceDescription();
        sd.setType("POTEGA");  //typ serwisu
        sd.setName(getName());  //nazwa usługi
        dfd.addServices(sd);
        //2. Rejestracja w serwisie:
        try{   //wychwycenie zduplikowanych wpisów
            DFService.register(this,dfd);
        }catch (FIPAException fe){
            fe.printStackTrace();
        }
    }
    protected void takeDown() {
        System.out.println("zaraz sie usune!");
        //3. Wyrejestrowanie z serwisu
        try{
            DFService.deregister(this);
        }catch (FIPAException fe){
            fe.printStackTrace();
        }
    }
}
