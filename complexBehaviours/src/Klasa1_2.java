import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Klasa1_2 extends Agent {
    private static final String STATE_A = "A";
    private static final String STATE_B = "B";
    private static final String STATE_C = "C";
    private static final String STATE_D = "D";
    private static final String STATE_E = "E";

    public Klasa1_2() {
    }

    protected void setup() {
        FSMBehaviour var1 = new FSMBehaviour(this) {
            public int onEnd() {
                this.myAgent.doDelete();
                return super.onEnd();
            }
        };
        var1.registerFirstState(new Klasa1_2.NamePrinter(), "A");
        var1.registerState(new Klasa1_2.RandomGenerator(1), "B");
        var1.registerState(new Klasa1_2.NamePrinter(), "C");
        var1.registerState(new Klasa1_2.RandomGenerator(1), "D");
        var1.registerLastState(new Klasa1_2.NamePrinter(), "E");
        var1.registerDefaultTransition("A", "B");
        var1.registerTransition("B", "C", 1);
        var1.registerTransition("B", "D", 0);
        var1.registerDefaultTransition("C", "D");
        var1.registerTransition("D", "E", 0);
        var1.registerTransition("D", "A", 1);
        this.addBehaviour(var1);
    }

    private class RandomGenerator extends Klasa1_2.NamePrinter {
        private int maxExitValue;
        private int exitValue;

        private RandomGenerator(int var2) {
            //super(null);
            this.maxExitValue = var2;
        }

        public void action() {
            super.action();
            this.exitValue = (int)(Math.round(Math.random()) * (long)this.maxExitValue);
            System.out.println("\tWylosowano: " + this.exitValue);
        }

        public int onEnd() {
            return this.exitValue;
        }
    }

    private class NamePrinter extends OneShotBehaviour {
        private NamePrinter() {
        }

        public void action() {
            System.out.println("Stan: " + this.getBehaviourName());
        }
    }
}