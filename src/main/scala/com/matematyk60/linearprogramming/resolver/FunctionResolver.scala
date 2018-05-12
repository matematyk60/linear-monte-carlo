package com.matematyk60.linearprogramming.resolver


import com.matematyk60.linearprogramming.parser.{CalculatorLexer, CalculatorParser}
import com.matematyk60.linearprogramming.visitor.{ValueCalculator, VarFinder}
import org.antlr.v4.runtime.{CharStream, CharStreams, CommonTokenStream}

class FunctionResolver {
  private val calculator = new ValueCalculator
  private val varFinder = new VarFinder

  def functionResolve(func: String, map: Map[String, Double] = Map()): (Double, Map[String, Double]) = this.synchronized {
    val stream = CharStreams.fromString(func)
    calculator.variableMap = map
    resolveValue(stream)
  }

  def resolveValue(stream: CharStream): (Double, Map[String, Double]) = {

    val tree = parser(stream).start
    (calculator.visit(tree), calculator.variableMap)
  }

  def findVars(func: String): Set[String] = {
    val stream = CharStreams.fromString(func)
    resolveVars(stream)
  }

  def resolveVars(stream: CharStream): Set[String] = {
    val tree = parser(stream).start

    //varFinder.visit(tree)
  }

  private def parser(stream: CharStream): CalculatorParser = {
    val lexer = new CalculatorLexer(stream)
    val tokens = new CommonTokenStream(lexer)
    new CalculatorParser(tokens)
  }
}
