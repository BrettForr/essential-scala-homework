package week1.calculator

/**
  * Created by brett on 4/2/2017.
  */

// TODO: monad... ?
sealed trait CalculationResult {
  def combine(that: CalculationResult, operation: (Double, Double) => CalculationResult): CalculationResult = (this, that) match {
    case (Failure(reason), _) => Failure(reason)
    case (_, Failure(reason)) => Failure(reason)
    case (Success(x), Success(y)) => operation(x, y)
  }
}
case class Failure(reason: String) extends CalculationResult
case class Success(value: Double) extends CalculationResult
