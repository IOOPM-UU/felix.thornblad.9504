package org.ioopm.calculator.test; 
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;  
import java.util.regex.Pattern;
import java.util.*;

/**
 * Taking input from the promt and evaluating it using CalculatorParser and the ast
 */ 

public class CalculatorTest {
    public static void main(String [] args) {
        Environment vars = new Environment();
        final CalculatorParser parse = new CalculatorParser();
        final EvaluationVisitor evaluator = new EvaluationVisitor();
        final NamedConstantChecker nChecker = new NamedConstantChecker();
        final ReassignmentChecker rChecker = new ReassignmentChecker();
        Scanner sc = new Scanner(System.in);
        int fullEval = 0;
        int enterd = 0;
        int evaluated = 0;
        double lastValue = 0;
      
        while(true) {
            String input = "";
            
            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                System.exit(0); 
            }
            
            Environment save = vars;
            try {
                SymbolicExpression parsed = parse.parse(input, vars);
                if(parsed.isCommand()) {
                    if (parsed instanceof Quit) {
                        System.out.println("");
                        break;
                    }
                    else if(parsed instanceof Clear) {
                        vars = new Environment();
                    }
                    
                    else if (parsed instanceof Vars) {
                        System.out.println(vars);
                    }
                    else if (parsed instanceof Ans) {
                        System.out.println(lastValue);
                    }
                }
                else {
                    if(!nChecker.check(parsed) || !rChecker.check(parsed)) {
                        continue;
                    }
                    SymbolicExpression evaled = evaluator.evaluate(parsed, vars);
                    System.out.println(evaled);
                    enterd ++;
                    evaluated ++;
                    if(evaled instanceof Constant) {
                        lastValue = evaled.getValue();
                        vars.put(new Variable("ans"),new Constant (lastValue));
                        fullEval ++;
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Cannot parse ");
                vars = save;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                vars = save;
            }
        }
    }
}
