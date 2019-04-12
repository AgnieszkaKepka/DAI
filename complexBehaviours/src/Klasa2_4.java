import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;


public class Klasa2_4 extends Agent {
    public Klasa2_4() {
    }

    protected void setup() {

        System.out.println("My name is " + this.getLocalName());

        ThreadedBehaviourFactory var1 = new ThreadedBehaviourFactory();

        CyclicBehaviour var2 = new CyclicBehaviour(this) {
            public void action() {
                System.out.println("cyclic_1");
            }
        };
        CyclicBehaviour var3 = new CyclicBehaviour(this) {
            public void action() {
                System.out.println("cyclic_2");
            }
        };
        this.addBehaviour(var1.wrap(var2));
        this.addBehaviour(var1.wrap(var3));
    }
}
