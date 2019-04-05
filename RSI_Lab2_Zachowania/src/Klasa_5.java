import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Klasa_5 extends Agent {
    private int step=0;
    protected void setup(){
        OneShotBehaviour oneShotBehaviour1=new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("pierwsze");
            }
        };
        addBehaviour(oneShotBehaviour1);


        addBehaviour(new Behaviour() {
            @Override
            public void action() {

                switch(step){
                    case 0:
                        OneShotBehaviour oneShotBehaviour2=new OneShotBehaviour() {
                            @Override
                            public void action() {
                                System.out.println("drugie");
                            }
                        };
                        addBehaviour(oneShotBehaviour2);
                        System.out.println("pierwszy krok.");
                        step++;
                        break;
                    case 1:
                        System.out.println("drugi krok.");
                        step++;
                        break;
                    case 2:
                        System.out.println("trzeci krok.");
                        step++;
                        break;
                }
            }

            @Override
            public boolean done() {
                return step==3;
            }
        });
    }

    protected void takeDown() {
        System.out.println("zaraz sie usune!");
    }
}