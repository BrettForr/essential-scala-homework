package week3.jsonHW

import JsUtil._

/**
  * Created by brett on 4/25/2017.
  */
trait JsWriter[T] {
  def write(value: T): JsValue
}
object JsWriter {

  def apply[T: JsWriter]: JsWriter[T] = implicitly[JsWriter[T]]

  implicit object AnonWitness extends JsWriter[Anonymous] {
    def write(value: Anonymous): JsValue = JsObject(visitorWrite(value))
  }
  implicit object UserWitness extends JsWriter[User] {
    def write(value: User): JsValue = JsObject(visitorWrite(value) +
      ("email" -> JsString(value.email))
    )
  }
  implicit object VisitorWitness extends JsWriter[Visitor] {
    def write(value: Visitor): JsValue = value match {
      case a: Anonymous => a.toJson
      case u: User => u.toJson
    }
  }

//  implicit def genericVisitorWitness[V <: Visitor](value: V): JsWriter[V] = value match {
//    case a: Anonymous => new JsWriter[Anonymous] {
//      override def write(value: Anonymous): JsValue = JsObject(visitorWrite(value))
//    }
//    case u: Visitor => new JsWriter[User] {
//      override def write(value: User): JsValue = JsObject(visitorWrite(value) +
//        ("email" -> JsString(value.email))
//      )
//    }
//  }
}


