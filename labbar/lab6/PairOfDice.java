public class PairOfDice {

    private Die die1;
    private Die die2;

    public PairOfDice() {
        this.die1 = new Die(6);
        this.die2 = new Die(6);
    }

    public PairOfDice(int numberOfSides) {
        if (numberOfSides < 2 || numberOfSides > 10) {
          throw new IllegalArgumentException(numberOfSides + " Illegal number of sides for die");
        }
        this.die1 = new Die(numberOfSides);
        this.die2 = new Die(numberOfSides);
    }

    public int rollBoth() {
        return die1.roll() + die2.roll();
    }
    
    public int readDie(int die) {
        if(die == 1) {
            return die1.get();
        }
        else {
            return die2.get();
        }
    }

    public String toString() {
        return "Die 1: " + die1.get() + ", Die 2: " + die2.get();
    }

}
