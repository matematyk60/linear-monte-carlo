package com.matematyk60.linearprogramming.resolver

import com.matematyk60.linearprogramming.PredsProvider
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._

class PredsProviderTest extends AssertionsForJUnit {

  @Test
  def simpleTest1(): Unit = {
    val provider = new PredsProvider
    val pred = provider.pred("x < 20")
    assertTrue(pred.test(Map("x" -> 2.0)))
  }

  @Test
  def simpleTest2(): Unit = {
    val provider = new PredsProvider
    val pred = provider.pred("x < 20")
    assertFalse(pred.test(Map("x" -> 21.0)))
  }

  @Test
  def polyTest(): Unit = {
    val provider = new PredsProvider
    val pred = provider.pred("x^2 - 3 * x + 5 < 10")
    assertTrue(pred.test(Map("x" -> 1.5)))
  }

  @Test
  def polyTest2(): Unit = {
    val provider = new PredsProvider
    val pred = provider.pred("x^2 - 3 * x + 5 < 10")
    assertFalse(pred.test(Map("x" -> 7.0)))
  }
}
