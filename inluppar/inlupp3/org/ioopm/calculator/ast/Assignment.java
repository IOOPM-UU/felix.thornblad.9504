 package org.ioopm.calculator.ast;
 
 public class Assignment extends Binary{
  public Assignment(final SymbolicExpression lhs, final SymbolicExpression rhs){
   super("Assignment", lhs, rhs);
  }

}