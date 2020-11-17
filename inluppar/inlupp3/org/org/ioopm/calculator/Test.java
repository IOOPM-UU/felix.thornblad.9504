import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.util.HashMap;

public class Test{
    
    
    public static void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }
    
    public static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
        SymbolicExpression r = e.eval(vars);
        SymbolicExpression t = expected.eval(vars);
        if (t.equals(r)) {
                System.out.println("Passed: " + r);
        } else {
                System.out.println("Error: expected '" + t + "' but got '" + r + "'");
        }
    }
    
    public static void testEquals(SymbolicExpression expected, SymbolicExpression e) {
    if (e.equals(expected)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

    
    public static void main (String [] args){
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);
        Constant c4 = new Constant(0);
        Variable v = new Variable("x");
        Variable t = new Variable("t");
        Assignment ass = new Assignment(c1,v);
        Assignment ass2 = new Assignment(ass,t);
        Addition a = new Addition(c1, c2);
        Addition a2 = new Addition(c1, a);
        Multiplication m = new Multiplication(c1, c2);
        Subtraction s = new Subtraction (c2, v);
        Division d = new Division (c2, c1);
        Cos c3 = new Cos (c4);
        Negation n = new Negation(c1);
        Sin s1 = new Sin(c1);
        
        NamedConstant pi = new NamedConstant("pi", Math.PI);
        //Assignment asspi = new Assignment(c1,pi);
        Addition api = new Addition(pi, c2);
        
        Environment vars = new Environment();
        
        
        testPrinting("(5.0 + x) * 2.0", m);
        testPrinting("2.0", c2);
        testPrinting("(2.0 - x) / 5.0", d);
        testPrinting("cos (0.0)", c3);
        testPrinting("(-5.0)", n);
        testPrinting("sin (5.0)", s1);
        
        //testEvaluating(c1, c2);
        testEvaluating(c1, c1, vars);
        testEvaluating(new Constant(12), a2, vars);
        testEvaluating(new Constant(1), c3, vars);
        testEvaluating(n, n, vars);
        testEvaluating(s1, s1, vars);
        
        testEvaluating(m, m, vars);
        testEvaluating(d, d, vars);
        vars = new Environment();
        ass.eval(vars);
        testEvaluating(v, v, vars);
        //testEvaluating(ass, ass, vars);
        //vars = new Environment();
        testEvaluating(ass2, ass2, vars);
        
        vars = new Environment();
        //testEvaluating(asspi, asspi, vars);
        testEvaluating(api, api, vars);
        
        
        SymbolicExpression c = new Addition(new Constant(5), new Constant(37));
        SymbolicExpression b = new Constant(42);
        testEvaluating(b, c, vars); /// Tests if reducing c returns b (it should!)


        
        testEquals(c1, c1);
        testEquals(a, a);
        testEquals(c3, c3);
        testEquals(s1, s1);
        testEquals(m, m);
        
    }

}