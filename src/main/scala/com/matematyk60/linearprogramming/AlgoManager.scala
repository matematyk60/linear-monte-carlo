package com.matematyk60.linearprogramming

import java.util.function.Predicate

import scala.concurrent.Await
import scala.io.StdIn
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.control.Breaks.break

class AlgoManager {

  val provider = new PointsProvider

  var func: String = ""

  var radius: Double = 1.0

  var numberOfSamples: Int = 0


  def start(): Unit = {
    provider.preds = new PredsProvider().scanPreds()
    val first = startSearching()
    println(s"Found maximum has value = ${first._1} and coordinates = ${first._2}")
    var lastResult = first
    while(askForContinue()) {
      val found = continueSearching(lastResult._2)
      if(found._1 <  lastResult._1 ) {
        println("Newly found maximum is not greater than previous value")
        println(s"Ending searching, found maximum has value = ${lastResult._1} and coordinates = ${lastResult._2}")
        break
      }
      lastResult = found
      println(s"Found maximum has value = ${lastResult._1} and coordinates = ${lastResult._2}")
    }
    println(s"Ending searching, found maximum has value = ${lastResult._1} and coordinates = ${lastResult._2}")
  }

  def askForContinue(): Boolean = {
    println("Do you want to continue searching?")
    StdIn.readLine() match {
      case "y" => true
      case "n" => false
      case _ => askForContinue()
    }
  }

  def startSearching(): (Double, Map[String, Double]) = {
    println("Please type function literal")
    func = StdIn.readLine()
    println("Please type in radius")
    radius = StdIn.readLine().toDouble
    println("Please type in number of random points")
    numberOfSamples = StdIn.readLine().toInt
    val f = provider.findPoints(func, numberOfSamples, radius)

    Await.result(f, Duration.Inf).filter(_.isDefined).map(_.get).maxBy(_._1)
  }

  def continueSearching(point: Map[String, Double]): (Double, Map[String, Double]) = {
    radius = radius / 2
    val f = provider.findPointsInSorround(func, numberOfSamples, radius, point)

    Await.result(f, Duration.Inf).filter(_.isDefined).map(_.get).maxBy(_._1)
  }

}

object AlgoManager {
  def main(args: Array[String]): Unit = {
    val manager = new AlgoManager
    manager.start()
  }
}
