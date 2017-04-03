package week1.calculator

/**
  * Created by brett on 4/2/2017.
  */
object CalculatorTest extends App {
  assert(SafeAddition(SafeSquareRoot(SafeNumber(-1.0)), SafeNumber(2.0)).eval == Failure("Square root of negative number"))
  assert(SafeAddition(SafeSquareRoot(SafeNumber(4.0)), SafeNumber(2.0)).eval == Success(4.0))
  assert(SafeDivision(SafeNumber(4), SafeNumber(0)).eval == Failure("Division by zero"))
}
