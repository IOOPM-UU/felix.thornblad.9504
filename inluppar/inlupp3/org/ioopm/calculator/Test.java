import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;

public class Test{
    
    
    public static void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }
    
    public static void testEvaluating(SymbolicExpression expected, SymbolicExpression e) {
    //SymbolicExpression r = e.eval();
    if (e.equals(expected)) {
        System.out.println("Passed: " + e);
    } else {
        System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
    }
}

    
    public static void main (String [] args){
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);
        Variable v = new Variable("x");
        Addition a = new Addition(c1, v);
        Multiplication m = new Multiplication(a, c2);
        Subtraction s = new Subtraction (c2, v);
        Division d = new Division (s, c1);
        Cos c3 = new Cos (v);
        Negation n = new Negation(c1);
        Sin s1 = new Sin(v);
        
        
        testPrinting("(5.0 + x) * 2.0", m);
        testPrinting("2.0", c2);
        testPrinting("(2.0 - x) / 5.0", d);
        testPrinting("cos (x)", c3);
        testPrinting("(-5.0)", n);
        testPrinting("sin (x)", s1);
        
        testEvaluating(c1, c2);
        testEvaluating(c1, c1);
        testEvaluating(a, a);
        testEvaluating(c3, c3);
        testEvaluating(n, n);
        
        //testEvaluating();
        
        SymbolicExpression c = new Addition(new Constant(5), new Constant(37));
        SymbolicExpression b = new Constant(42);
        testEvaluating(b, c); /// Tests if reducing a returns b (it should!)



    }

}