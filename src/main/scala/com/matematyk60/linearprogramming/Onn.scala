package com.matematyk60.linearprogramming

object Onn extends App {
  val m: Shape = Circle()

  m match {
    case Circle() => "SDW"
  }




}

class Shape
case class Circle() extends Shape
case class Triangle() extends Shape