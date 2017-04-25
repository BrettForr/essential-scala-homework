package week3.jsonHW

/**
  * Created by am_dev on 4/25/17.
  */
object JsUtil {
  def visitorWrite[T <: Visitor](value: T): Map[String, JsString] = Map(
    "id" -> JsString(value.id),
    "createdAt" -> JsString(value.createdAt.toString),
    "age" -> JsString(value.age.toString)
  )

  implicit class JsWriterExtensions[T: JsWriter](writable: T) {
    def toJson: JsValue = JsWriter[T].write(writable)
  }
}
