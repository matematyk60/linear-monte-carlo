package com.matematyk60.linearprogramming.visitor

import com.matematyk60.linearprogramming.parser.{CalculatorBaseVisitor, CalculatorParser}

class VarFinder extends CalculatorBaseVisitor[Unit] {
  private var varSet = Set[String]()

  override def visitVariable(ctx: CalculatorParser.VariableContext): Unit = {
    varSet = varSet + ctx.ID().getSymbol.getText
  }

  def variableSet: Set[String] = varSet
  def flush(): Unit = {
    varSet = Set()
  }
}
