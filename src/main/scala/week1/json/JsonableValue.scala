package week1.json

/**
  * Created by brett on 4/2/2017.
  */
sealed trait JsonableValue[T]
object JsonableValue {
  implicit object DoubleWitness extends JsonableValue[Double]
  implicit object IntWitness extends JsonableValue[Int]
  implicit object StringWitness extends JsonableValue[String]
  implicit object BooleanWitness extends JsonableValue[Boolean]
}
