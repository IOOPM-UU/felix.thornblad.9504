import java.util.Scanner;

public class NameOrder {

    public static void main(String [] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Name 1: ");
        String name1 = sc.next();
        System.out.print("Name 2: ");
        String name2 = sc.next();
        if(name1.compareTo(name2) <= 0)
        {
            System.out.println("Name 1: " + name1 + ", Name 2: " + name2);
        }
        else
        {
            System.out.println("Name 2: " + name2 + ", Name 1: " + name1);
        }
        sc.close();
    }

}
