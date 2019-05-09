import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Random;


public class Zlecajacy extends Agent {

    Zlecajacy my;
    Random random;
    int number;


    protected void setup() {
        super.setup();

        my = this;
        random = new Random();
        number = 0;

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                number = (int)(Math.round(random.nextDouble()*20));
                System.out.println("Wysolowana liczba:   " + number);
            }
        });

        FSMBehaviour fsmBehaviour = new FSMBehaviour(this) {
            public int onEnd() {
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println("Sprawdzanie uslugi POTEGA");

                DFAgentDescription dfd = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType( "POTEGA" );
                dfd.addServices(sd);
                //dfd.addServices(sd);

                SearchConstraints ALL = new SearchConstraints();
                ALL.setMaxResults(new Long(-1));
                try
                {
                    DFAgentDescription[] result = DFService.search(myAgent, dfd, ALL);
                    System.out.println("Liczba uslug:    " + result.length);
                    System.out.println(" ");
                    if(result.length>0) {
                        send(result[0].getName());
                        recieve(this);
                    }
                }
                catch (FIPAException fe) {
                    System.out.println("ERROR"); }
            }
        });
    }

    private void send(AID aid) {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(aid);
        msg.setContent(Integer.toString(number));
        send(msg);
        System.out.println("zlecajacy wysyla request");
    }
    private void recieve(Behaviour b) {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg1 = blockingReceive(mt);
        if(msg1!= null) {
            if(msg1.getPerformative() == ACLMessage.INFORM){
                System.out.println("zlecajacy odbiera inform " +  msg1.getContent());
                my.doDelete();
            }
        }
        else {
            b.block();
        }
    }
    protected void takeDown() {
        System.out.println("zlecajacy konczy zadanie ");
    }




}
