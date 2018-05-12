package com.matematyk60.linearprogramming.resolver

import java.time.LocalTime

import com.matematyk60.linearprogramming.PointsProvider
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class PointsProviderTest extends AssertionsForJUnit {
  val picker = new PointsProvider

  @Test
  def func1(): Unit = {
    val f = picker.findTenPoints("x1")
    f onSuccess {
      case t => println(t)
    }
    Await.result(f, Duration.Inf)
  }

  @Test
  def func2(): Unit = {
    val f = picker.findTenPoints("x1 + x2")

    f onSuccess {
      case t => println(t)
    }
    Await.result(f, Duration.Inf)
  }

  @Test
  def func3(): Unit = {
    val time = LocalTime.now()
    val f = picker.findPoints("x1 + x2 + x3 + x4 / x1", 100000)
    f onSuccess {
      case t =>
        println(t)
        println(LocalTime.now().toSecondOfDay - time.toSecondOfDay)
    }
    Await.result(f, Duration.Inf)
  }
}
