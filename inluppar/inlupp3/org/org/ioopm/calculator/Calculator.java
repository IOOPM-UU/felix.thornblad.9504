import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.util.Scanner;

public class Calculator {
    public static void main(String [] args) {
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);
        Variable v = new Variable("x");
        Addition a = new Addition(c1, v);
        Multiplication m = new Multiplication(a, c2);
        
        
        System.out.println ("(5.0 + x) * 2.0 ==> " + m);
    }
}