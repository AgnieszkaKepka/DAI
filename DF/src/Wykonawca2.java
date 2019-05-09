import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;


public class Wykonawca2 extends Agent {

    protected void setup() {
        System.out.println("start!");

        //Rejestracja usługi:
        // 1. Przygotowanie opisu agenta:
        DFAgentDescription dfd=new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd=new ServiceDescription();
        sd.setType("POTEGA");
        sd.setName(getName());
        dfd.addServices(sd);

        //2.Rejestracja w serwisie:
        try{
            DFService.register(this,dfd);
        }catch (FIPAException fe){
            fe.printStackTrace();
        }

        addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    if (aclMessage.getPerformative() == ACLMessage.REQUEST) {
                        String count_message = aclMessage.getContent();
                        System.out.println("The content of the message: " + count_message);
                        //Parsuje liczbę podaną w treści wiadomości
                        double message_number = Double.parseDouble(count_message);
                        //Oblicza wynik podnosząc do drugiej potęgi otrzymaną liczbę
                        double score = message_number*message_number;
                        AID sender = aclMessage.getSender();
                        System.out.println("Sent message: " + Double.toString(score));
                        ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                        response.addReceiver(sender);
                        response.setContent(Double.toString(score));
                        send(response);
                    }
                    if (aclMessage.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
                        aclMessage.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                        aclMessage.setContent("nie_rozumiem");
                    }
                }
                else {
                    block();
                }
            }
        } );
    }
    protected void takeDown() {
        System.out.println("!!!");

        //Wyrejestrowanie z serwisu
        try{
            DFService.deregister(this);
        }catch (FIPAException fe){
            fe.printStackTrace();
        }
    }



}
