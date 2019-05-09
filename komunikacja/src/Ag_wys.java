import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import jade.lang.acl.ACLMessage;



public class Ag_wys extends Agent {
    private int step = 0;
    protected void setup() {
        System.out.println("startuję!");

        addBehaviour(new Behaviour() {
            @Override
            public void action() {
                switch(step){
                    case 0:
                        System.out.println("pierwszy krok");
                        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                        msg.addReceiver(new AID("Ala",AID.ISLOCALNAME));
                        msg.setContent("Hello!");
                        send(msg);
                        step++;
                        break;
                    case 1:
                        System.out.println("drugi krok");
                        ACLMessage jmsg = receive();
                        if (jmsg != null){
                            System.out.println(jmsg.getContent());
                                step=0;

                        }
                        else
                            block();
                        break;
                }
            }

            @Override
            public boolean done() {
                return step==2;
            }
        });
    }
    protected void takeDown(){
        System.out.println("zaraz się usunę!");
    }

}





