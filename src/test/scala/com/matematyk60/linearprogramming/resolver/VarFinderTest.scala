package com.matematyk60.linearprogramming.resolver

import org.junit.Test
import org.junit.Assert.assertEquals
import org.scalatest.junit.AssertionsForJUnit

class VarFinderTest extends AssertionsForJUnit {
  val resolver = new FunctionResolver

  @Test
  def func1(): Unit = {
    val setted = resolver.findVars("2 + x1 * 2")
    assertEquals(Set("x1"), setted)
  }

  @Test
  def func2(): Unit = {
    val setted = resolver.findVars("2 + x1 * 2 + sin ( x2 + x3 * x4 )")
    assertEquals(Set("x1", "x2", "x3", "x4"), setted)
  }

}
