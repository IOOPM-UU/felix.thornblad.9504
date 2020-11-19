package org.ioopm.calculator;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random; 
import org.ioopm.calculator.ast.*;

public class TestTest {
    private Random rng = new Random();
    
//    private static void testPrinting(String expected, SymbolicExpression e) {
//         if (expected.equals("" + e)) {
//             System.out.println("Passed: " + e);
//         } 
//         else {
//             System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
//         }
//    }
//    private static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
//         SymbolicExpression r = e.eval(vars);
//         if (r.equals(expected)) {
//             System.out.println("Passed: " + e + " evaluated to: " + r);
//         } else {
//             System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
//         }
//    }
    
    @Before
    public void init() {
        rng = new Random(System.currentTimeMillis());
    }
    
    @Test
    public void getValueConstant() {
        double value = rng.nextDouble();
        Constant c = new Constant(value);
        assertEquals(value, c.getValue(), 0f);
    }
}