package week1.calculator

import scala.math.sqrt

/**
  * Created by brett on 4/2/2017.
  */
sealed trait Expr {
  def eval: Double = this match {
    case Number(v) => v
    case Addition(l, r) => l.eval + r.eval
    case Subtraction(l, r) => l.eval + r.eval
    case Division(l, r) => l.eval / r.eval
    case SquareRoot(v) => sqrt(v)
  }
}
final case class Addition(left: Expr, right: Expr) extends Expr
final case class Subtraction(left: Expr, right: Expr) extends Expr
final case class Division(left: Expr, right: Expr) extends Expr
final case class SquareRoot(value: Double) extends Expr
final case class Number(value: Double) extends Expr
