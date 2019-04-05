import jade.core.Agent;

public class AgentTry extends Agent {

    protected void setup(){
        System.out.println("Startuje");
    }

    protected void takeDown(){
        System.out.println("Zaraz sie usune");
    }

}
