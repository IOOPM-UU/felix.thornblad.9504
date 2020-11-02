import java.util.Scanner;

public class MyDieTest {

    public static void main(String [] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Number of sides: ");
      int sides = sc.nextInt();
      Die d = new Die(sides);
      int sum = 0; 
      for (int i = 0; i < 10; i++) {
         sum += d.roll(); 
      }
      System.out.println("The sum of 10 rolls: " + sum);
      Die d1 = new Die();
      System.out.println(d1);
      Die d2 = new Die(3);
      Die d3 = new Die(3);
      boolean result = d2.equals(d3);
      System.out.println("Equal: " + result);

      System.out.print("Number of sides of pair: ");
      sides = sc.nextInt();
      PairOfDice pair = new PairOfDice(sides);
      int pair_sum = pair.rollBoth();
      int readDie1 = pair.readDie(1);
      System.out.println("Sum: " + pair_sum + "\n" + "Die 1: " + readDie1 + "\n" + "toString: " + pair.toString());

      sc.close();
    }
}

