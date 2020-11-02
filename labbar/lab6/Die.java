import java.util.Scanner;

public class Die {
  private int numberOfSides;
  private int value;

  public Die() {
      this.numberOfSides = 6;
      this.value = 0;
  }

  public Die(int numberOfSides) {
      if (numberOfSides < 2 || numberOfSides > 10) {
          throw new IllegalArgumentException(numberOfSides + " Illegal number of sides for die");
      }
      this.numberOfSides = numberOfSides;
  }

  public int roll() {
    this.value = (int) (Math.random() * numberOfSides) + 1;
    return this.get();
  }

  public int get() {
    if(value == 0)
    {
      throw new IllegalArgumentException("The die needs to be roll() ed before it has a value");
    }
      return value;
  }

  public String toString() {
      return "Die(" + value + ")";
  }

  boolean equals(Die otherDie) {
      if (otherDie.numberOfSides == numberOfSides) {
          return true;
      }  
      else {
          return false;
      }
  }

  public static void main(String [] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Number of sides: ");
      int sides = sc.nextInt();
      Die d = new Die(sides);
      Die nonRolled = new Die();
      System.out.println("Roll: " + d.roll() + ", get:" + nonRolled.get());
      sc.close();
  }
}
