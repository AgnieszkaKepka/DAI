import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;

public class Klasa2_2 extends Agent {
    private int step = 0;

    public Klasa2_2(){

    }

    protected void setup(){

        System.out.println("startuje");

        final ParallelBehaviour var1 = new ParallelBehaviour();

        var1.addSubBehaviour(new OneShotBehaviour() {
            public void action() {
                System.out.println("pierwszy krok!");
            }
        });
        var1.addSubBehaviour(new OneShotBehaviour() {
            public void action() {
                System.out.println("drugi krok!");
            }
        });

        var1.addSubBehaviour(new OneShotBehaviour() {
            public void action() {
                System.out.println("trzeci krok!");

                Klasa2_2.this.removeBehaviour(var1);
                System.out.println("usuwam!");
            }
        });

        this.addBehaviour(var1);
    }
    protected void takeDown() {
        System.out.println("zaraz sie usune!");
    }
}
