package week3.jsonHW

import java.util.Date
import JsUtil._

import scala.annotation.implicitNotFound

/**
  * Created by brett on 4/25/2017.
  */
@implicitNotFound(s"Could not find {T}")
trait JsWriter[T] {
  def write(value: T): JsValue
}
object JsWriter {
  def apply[T: JsWriter]: JsWriter[T] = implicitly[JsWriter[T]]

  implicit object DateWitness extends JsWriter[Date] {
    def write(value: Date): JsValue = JsString(value.toString)
  }
  implicit object StringWitness extends JsWriter[String] {
    def write(value: String): JsValue = JsString(value)
  }

}


