import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Klasa_3 extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
                public void action() {
                    System.out.println("wykonuje");
                }
            });
        }

    protected void takeDown(){
        System.out.println("Zaraz sie usune");
    }


}
