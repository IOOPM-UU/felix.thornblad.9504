package org.ioopm.calculator.test;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import java.util.Random; 


/**
 * Taking input from the promt and evaluating it using CalculatorParser and the ast
 */ 

public class ParserTest {
    Environment vars = new Environment();
    final CalculatorParser parse = new CalculatorParser();
    private final EvaluationVisitor evaluator = new EvaluationVisitor();
    
    @Test
    public void parseConstant() {
        Constant c = new Constant(42);
        try {
            SymbolicExpression s = parse.parse(c.toString(), vars);
            assertTrue(c.equals(s));
        } catch(Exception e) {
            System.out.println("Cannot parse ");
        }
    }
    
    @Test
    public void parseAddition() {
        Addition a = new Addition(new Constant(42), new Negation(new Constant(4711)));
        try {
            SymbolicExpression s = 
                evaluator.evaluate(parse.parse(a.toString(), vars), vars, vars);
                
            SymbolicExpression as = evaluator.evaluate(a, vars, vars);
            assertTrue(as.equals(s));
        } catch(Exception e) {
            System.out.println("Cannot parse ");
        }
    }
    
    @Test
    public void parseAssignment() {
        Assignment a = new Assignment(new Constant(42), new Variable("x"));
        try {
            SymbolicExpression s = 
                evaluator.evaluate(parse.parse(a.toString(), vars), vars, vars);
                
            SymbolicExpression as = evaluator.evaluate(a, vars, vars);
            assertTrue(as.equals(s));
        } catch(Exception e) {
            System.out.println("Cannot parse ");
        }
    }
    
    @Test
    public void parseNegation() {
        Negation n = new Negation(new Negation(new Negation(new Constant(1))));
        try {
            SymbolicExpression s = 
                evaluator.evaluate(parse.parse(n.toString(), vars), vars, vars);
                
            SymbolicExpression as = evaluator.evaluate(n, vars, vars);
            assertTrue(as.equals(s));
        } catch(Exception e) {
            System.out.println("Cannot parse ");
        }
    }
    
    @Test
    public void parseParentheses() {
        Constant c = new Constant(42);
        try {
            SymbolicExpression s = parse.parse("("+ c.toString(), vars);
            System.out.println("Did parse?");
            assertTrue(false);
        } catch(Exception e) {
            assertTrue(true);
        }
    }
}