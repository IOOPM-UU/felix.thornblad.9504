package org.ioopm.calculator.ast;

public class Addition extends Binary{
       public Addition (final SymbolicExpression lhs, final SymbolicExpression rhs){
          super(lhs, rhs);
           //int i=Integer.parseInt(s); 
         
           //evaluateExpression
           
           //toString
       }
       @Override
       public String getName (){
           return "+";
       }
       
       @Override
       public int getPriority (){
           return 50;
       }
}