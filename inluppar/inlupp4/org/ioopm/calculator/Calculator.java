package org.ioopm.calculator;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.util.ArrayList; 
import java.util.Scanner;
import java.io.IOException;

/**
 * Taking input from the promt and evaluating it using CalculatorParser and the ast
 */ 

public class Calculator {
    public static void main(String [] args) {
        Environment vars = new Environment();
        Environment functions = new Environment();
        final CalculatorParser parse = new CalculatorParser();
        final EvaluationVisitor evaluator = new EvaluationVisitor();
        final NamedConstantChecker nChecker = new NamedConstantChecker();
        final ReassignmentChecker rChecker = new ReassignmentChecker();
        int enterd = 0;
        int evaluated = 0;
        int fullEval = 0;
        double lastValue = 0;
        System.out.println("Welcome to our java claculator! Start by entering an expression or command.");
        
        while (true) {
            Environment save = vars;
            String input = System.console().readLine();
            try {           
                if (input.length() >= 8 && input.substring(0, 8).equals("function")) {
                    ArrayList<String> params = new ArrayList<>();
                    input = input.replaceFirst("function ", "");
                    input = input.replaceAll(" ", "");
                    String[] parts = input.split("[(]");
                    Variable name = new Variable(parts[0]);
                    if(vars.containsKey(name)) {
                        throw new SyntaxErrorException("Error: Cannot use variable name as function");
                    }
                    parts[1] = parts[1].replaceFirst("[)]", "");
                    if(parts[1].length() != 0) {
                        parts = parts[1].split(",");
                        for (int i = 0; i < parts.length; i++) {
                                params.add(parts[i]);
                        }
                    } else {
                        parts = new String[0];
                    }
                    ArrayList<SymbolicExpression> body = new ArrayList<>();
                    System.out.print(" ");
                    input = System.console().readLine();
                    while (!input.equals("end")) {
                        SymbolicExpression parsed = parse.parse(input, vars);
                        if (!parsed.isCommand() && nChecker.check(parsed) && rChecker.check(parsed)) {
                            body.add(parsed);
                        } else {
                            throw new IllegalExpressionException("illegal declaration");
                        }
                        System.out.print(" ");
                        input = System.console().readLine();
                    }
                    Sequence sequence = new Sequence(params, body);
                    
                    FunctionDeclaration fun = new FunctionDeclaration(name, sequence);
                    functions.put(name, fun);
                    
                } else {
                    SymbolicExpression parsed = parse.parse(input, functions);
                    if(parsed.isCommand()) {
                        if (parsed instanceof Quit){
                            break;
                        }
                        else if(parsed instanceof Clear) {
                            vars = new Environment();
                        }
                        
                        else if (parsed instanceof Vars){
                            System.out.println(vars);
                        }
                        else if (parsed instanceof Ans){
                            System.out.println(lastValue);
                        }
                    }
                    else {
                        if (!nChecker.check(parsed) || !rChecker.check(parsed)) {
                            continue;
                        }
                        SymbolicExpression evaled = evaluator.evaluate(parsed, vars, functions);
                        System.out.println(evaled);
                        enterd ++;
                        evaluated ++;
                        if(evaled instanceof Constant){
                            lastValue = evaled.getValue();
                            vars.put(new Variable("ans") ,new Constant(lastValue));
                            fullEval ++;
                        }
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
        
    System.out.println("Thanks for using Java Calculator");
    System.out.println("You have enterd " + enterd + " expression,");
    System.out.println("of which " + evaluated + " were eveluated and " + fullEval + " fully evaluated.");
    }
}

  