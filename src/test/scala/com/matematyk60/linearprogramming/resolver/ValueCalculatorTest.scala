package com.matematyk60.linearprogramming.resolver


import org.junit.Assert.assertEquals
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

class ValueCalculatorTest extends AssertionsForJUnit {

  val resolver = new FunctionResolver

  @Test def equation1(): Unit = {
    assertEquals(3.0, resolver.functionResolve("3")._1, 0.01)
  }

  @Test def equation2(): Unit = {
    assertEquals(Math.PI, resolver.functionResolve("pi")._1, 0.01)
  }

  @Test def equation3(): Unit = {
    assertEquals(0, resolver.functionResolve("sin(pi)")._1, 0.01)
  }

  @Test def equation42(): Unit = {
    assertEquals(24.0, resolver.functionResolve("4 * 3 * 2")._1, 0.01)
  }

  @Test def equation41(): Unit = {
    assertEquals(32.0, resolver.functionResolve("4 * ( 3 + 5 )")._1, 0.01)
  }

  @Test def equation4(): Unit = {
    assertEquals(56.0, resolver.functionResolve("4 * 3 * 2 + 4 * ( 3 + 5 )")._1, 0.01)
  }

  @Test def equation5(): Unit = {
    assertEquals(6.0, resolver.functionResolve("4 + 2 * cos ( 2 * pi)")._1, 0.01)
  }

  @Test def function1(): Unit = {
    assertEquals(6.0, resolver.functionResolve("x + 3 * 2 - 2", Map("x" -> 2.0))._1, 0.01)

    assertEquals(6.5, resolver.functionResolve("x + 3 * 2 - 2", Map("x" -> 2.5))._1, 0.01)

    assertEquals(2.0, resolver.functionResolve("x + 3 * 2 - 2", Map("x" -> -2.0))._1, 0.01)
  }

  @Test def function2(): Unit = {
    assertEquals(0.5, resolver.functionResolve("x1 / 4 + 3 * sin(x2)", Map("x1" -> 2.0, "x2" -> Math.PI))._1, 0.01);
  }

  @Test def function3(): Unit = {
    assertEquals(0, resolver.functionResolve("x1 * (x1 - 3) + sin( 3 / 2 * x2 ) + x3",
      Map("x1" -> 2.0, "x2" -> Math.PI, "x3" -> 3.0))._1, 0.01)
  }

}
