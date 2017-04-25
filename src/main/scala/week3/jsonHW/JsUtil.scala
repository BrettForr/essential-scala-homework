package week3.jsonHW

/**
  * Created by am_dev on 4/25/17.
  */
object JsUtil {
  def visitorWrite[T <: Visitor](value: T): Map[String, JsValue] = Map(
    "id" -> value.id.toJson,
    "createdAt" -> value.createdAt.toJson,
    "age" -> JsString(value.age.toString)
  )

  implicit class JsWriterExtensions[T: JsWriter](writable: T) {
    def toJson: JsValue = JsWriter[T].write(writable)
  }
}
