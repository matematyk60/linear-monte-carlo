package com.matematyk60.linearprogramming

import com.matematyk60.linearprogramming.resolver.FunctionResolver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.blocking
import scala.util.Random

class PointsProvider {
  private val resolver = new FunctionResolver
  var preds: Set[PointPredicate] = Set[PointPredicate]()

  def findTenPoints(func: String): Future[IndexedSeq[Option[(Double, Map[String, Double])]]] = {

    findPoints(func, 10)
  }

  def findRandomPoint(varSet: Set[String], func: String, radius: Double): Future[Option[(Double, Map[String, Double])]] = {
    Future {
      val point = varSet.map(s => (s, Random.nextDouble() * radius)).toMap[String,Double]
      if(!preds.forall(p => p.test(point))) None
      else blocking {
        Some(resolver.functionResolve(func, point))
      }
    }
  }

  def findPoints(func: String, samples: Int, radius: Double = 1): Future[IndexedSeq[Option[(Double, Map[String, Double])]]] = {
    val varSet = resolver.findVars(func)
    val f = (1 to samples).map(_ => findRandomPoint(varSet, func, radius))

    Future.sequence(f)
  }


  def findPointsInSorround(func: String, samples: Int, radius: Double = 1, point: Map[String, Double]): Future[IndexedSeq[Option[(Double, Map[String, Double])]]] = {
    val varSet = resolver.findVars(func)
    if(varSet != point.keySet) {
      throw new IllegalArgumentException("Provided point dimension must match function dimension")
    }
    val f = (1 to samples).map(_ => findRandomPointInSorround(func, point, radius))

    Future.sequence(f)
  }

  def findRandomPointInSorround(func: String, point: Map[String, Double], radius: Double): Future[Option[(Double, Map[String, Double])]] = {
    Future {
      val varSet = point.keySet
      val newPoint = varSet.map(s => (s, point(s) + Random.nextDouble() * sign() * radius)).toMap[String,Double]
      if(!preds.forall(p => p.test(newPoint))) None
      else blocking {
        Some(resolver.functionResolve(func, newPoint))
      }
    }
  }

  private def sign(): Int = {
    val r = Random.nextBoolean()
    if(r) -1 else 1
  }
















  def findRandomPointSync(varSet: Set[String], func: String): (Double, Map[String, Double]) = {
    val point = varSet.map(s => (s, Random.nextDouble())).toMap[String,Double]
    resolver.functionResolve(func)
  }

  def findMaxSync(func: String, samples: Int): (Double, Map[String, Double]) = {
    val varSet = resolver.findVars(func)
    val f = (1 to samples).map(_ => findRandomPointSync(varSet, func))

    f.maxBy(_._1)
  }
}
