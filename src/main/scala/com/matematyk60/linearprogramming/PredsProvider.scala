package com.matematyk60.linearprogramming

import java.util.function.Predicate

import com.matematyk60.linearprogramming.resolver.FunctionResolver

import scala.io.StdIn

class PredsProvider {
  def scanpred(): PointPredicate = {
    println("Please type in limiting equation")
    val readed = StdIn.readLine()
    pred(readed)
  }

  def scanPreds(): Set[PointPredicate] = {
    var limits = Set[PointPredicate]()
    while(askForContinue()) {
      val pred = scanpred()
      limits = limits + pred
    }
    limits
  }

  def pred(equation: String): PointPredicate = {
    equation match {
      case s if s.contains('<') =>
        val splitted = s.split('<')
        new PointPredicate(splitted(0), new LessThan(splitted(1).toDouble))

      case s if s.contains('>') =>
        val splitted = s.split('>')
        new PointPredicate(splitted(0), new GreaterThan(splitted(1).toDouble))
    }
  }

  def askForContinue(): Boolean = {
    println("Do you want to add more limits?")
    StdIn.readLine() match {
      case "y" => true
      case "n" => false
      case _ => askForContinue()
    }
  }
}

class GreaterThan(value: Double) extends Predicate[Double] {
  override def test(t: Double): Boolean = t > value
}


class LessThan(value: Double) extends Predicate[Double] {
  override def test(t: Double): Boolean = t < value
}

class PointPredicate(func: String, cond: Predicate[Double]) extends Predicate[Map[String, Double]] {

  override def test(t: Map[String, Double]): Boolean = {
    val solver = new FunctionResolver
    cond.test(solver.functionResolve(func, t)._1)
  }
}