package week3.jsonHW

import java.util.Date

import week3.jsonHW.JsUtil._

/**
  * Created by brett on 4/25/2017.
  */
sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}
final case class Anonymous (id: String, createdAt: Date = new Date()) extends Visitor
final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

object Visitor {
  implicit object AnonWitness extends JsWriter[Anonymous] {
    def write(value: Anonymous): JsValue = JsObject(visitorWrite(value))
  }
  implicit object UserWitness extends JsWriter[User] {
    def write(value: User): JsValue = JsObject(visitorWrite(value) +
      ("email" -> value.email.toJson)
    )
  }
  implicit object VisitorWitness extends JsWriter[Visitor] {
    def write(value: Visitor): JsValue = value match {
      case a: Anonymous => a.toJson
      case u: User => u.toJson
    }
  }
}