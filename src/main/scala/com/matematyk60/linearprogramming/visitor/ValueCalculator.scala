package com.matematyk60.linearprogramming.visitor

import com.matematyk60.linearprogramming.parser.{CalculatorBaseVisitor, CalculatorParser}

class ValueCalculator extends CalculatorBaseVisitor[Double] {

  var variableMap: Map[String, Double] = Map()

  override def visitStart(ctx: CalculatorParser.StartContext): Double = visit(ctx.plusOrMinus)

  override def visitPlus(ctx: CalculatorParser.PlusContext): Double = visit(ctx.plusOrMinus) + visit(ctx.multOrDiv)

  override def visitMinus(ctx: CalculatorParser.MinusContext): Double = visit(ctx.plusOrMinus) - visit(ctx.multOrDiv)

  override def visitMultiplication(ctx: CalculatorParser.MultiplicationContext): Double = visit(ctx.multOrDiv) * visit(ctx.pow)

  override def visitDivision(ctx: CalculatorParser.DivisionContext): Double = visit(ctx.multOrDiv) / visit(ctx.pow)

  override def visitPower(ctx: CalculatorParser.PowerContext): Double = {
    if (ctx.pow != null) return Math.pow(visit(ctx.unaryMinus), visit(ctx.pow))
    visit(ctx.unaryMinus)
  }

  override def visitChangeSign(ctx: CalculatorParser.ChangeSignContext): Double = -1 * visit(ctx.unaryMinus)

  override def visitConstantPI(ctx: CalculatorParser.ConstantPIContext): Double = Math.PI

  override def visitConstantE(ctx: CalculatorParser.ConstantEContext): Double = Math.E

  override def visitDouble(ctx: CalculatorParser.DoubleContext): Double = ctx.DOUBLE.getText.toDouble

  override def visitInt(ctx: CalculatorParser.IntContext): Double = ctx.INT.getText.toDouble

  override def visitVariable(ctx: CalculatorParser.VariableContext): Double = {
    val varName = ctx.ID.getSymbol.getText
    variableMap(varName)
  }

  override def visitBraces(ctx: CalculatorParser.BracesContext): Double = visit(ctx.plusOrMinus)

  override def visitFuncCos(ctx: CalculatorParser.FuncCosContext): Double = Math.cos(visit(ctx.plusOrMinus))

  override def visitFuncSin(ctx: CalculatorParser.FuncSinContext): Double = Math.sin(visit(ctx.plusOrMinus))

  override def visitFuncTan(ctx: CalculatorParser.FuncTanContext): Double = Math.tan(visit(ctx.plusOrMinus))

  override def visitFuncAtan(ctx: CalculatorParser.FuncAtanContext): Double = Math.atan(visit(ctx.plusOrMinus))

  override def visitFuncAsin(ctx: CalculatorParser.FuncAsinContext): Double = Math.asin(visit(ctx.plusOrMinus))

  override def visitFuncAcos(ctx: CalculatorParser.FuncAcosContext): Double = Math.acos(visit(ctx.plusOrMinus))

  override def visitFuncLn(ctx: CalculatorParser.FuncLnContext): Double = Math.log(visit(ctx.plusOrMinus))

  override def visitFuncLog(ctx: CalculatorParser.FuncLogContext): Double = Math.log10(visit(ctx.plusOrMinus))

  override def visitFuncSqrt(ctx: CalculatorParser.FuncSqrtContext): Double = Math.sqrt(visit(ctx.plusOrMinus))



}
