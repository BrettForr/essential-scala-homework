package week1.calculator

import scala.math.sqrt

/**
  * Created by brett on 4/2/2017.
  */
sealed trait SafeExpr {
  def eval: CalculationResult = this match {
    case SafeNumber(v) => Success(v)
    case SafeAddition(l, r) => l.eval.combine(r.eval, (x, y) => Success(x + y))
    case SafeSubtraction(l, r) => l.eval.combine(r.eval, (x, y) => Success(x - y))
    case SafeDivision(l, r) => l.eval.combine(r.eval, (x, y) => y match {
      case 0 => Failure("Division by zero")
      case nonZero => Success(x / y)
    })
    case SafeSquareRoot(v) => v.eval match {
      case Success(x) =>  if (x < 0.0) Failure("Square root of negative number") else Success(sqrt(x))
      case Failure(r) => Failure(r)
    }

  }
}
final case class SafeAddition(left: SafeExpr, right: SafeExpr) extends SafeExpr
final case class SafeSubtraction(left: SafeExpr, right: SafeExpr) extends SafeExpr
final case class SafeDivision(left: SafeExpr, right: SafeExpr) extends SafeExpr
final case class SafeSquareRoot(value: SafeExpr) extends SafeExpr
final case class SafeNumber(value: Double) extends SafeExpr
