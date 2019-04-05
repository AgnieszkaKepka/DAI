import jade.core.Agent;
        import jade.core.behaviours.Behaviour;
        import jade.core.behaviours.OneShotBehaviour;

public class Klasa_2 extends Agent {

    protected void setup() {

        addBehaviour(new OneShotBehaviour() {
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
