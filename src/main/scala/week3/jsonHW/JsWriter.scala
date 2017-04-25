package week3.jsonHW

/**
  * Created by brett on 4/25/2017.
  */
trait JsWriter[T] {
  def write(value: T): JsValue
}
object JsWriter {
  def apply[T: JsWriter]: JsWriter[T] = implicitly[JsWriter[T]]

  implicit class JsUtil[T: JsWriter](writable: T) {
    def toJson= JsWriter[T].write(writable)
  }

  implicit object AnonWitness extends JsWriter[Anonymous] {
    def write(value: Anonymous) = JsObject(Map(
      "id" -> JsString(value.id),
      "createdAt" -> JsString(value.createdAt.toString),
      "age" -> JsString(value.age.toString)
    ))
  }
  implicit object UserWitness extends JsWriter[User] {
    def write(value: User) = JsObject(Map(
      "id" -> JsString(value.id),
      "createdAt" -> JsString(value.createdAt.toString),
      "age" -> JsString(value.age.toString),
      "email" -> JsString(value.email)
    ))
  }
  implicit object VisitorWitness extends JsWriter[Visitor] {
    def write(value: Visitor) = value match {
      case a: Anonymous => a.toJson
      case u: User => u.toJson
    }
  }
}

object JsUtil {
  def toJson[T: JsWriter](value: T) = JsWriter[T].write(value)
}
