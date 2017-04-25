package week3.jsonHW

/**
  * Created by brett on 4/25/2017.
  */
sealed trait JsValue {
  def stringify: String
}
final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  def stringify = values
    .map{case (name, value) => "\"" + name + "\":" + value.stringify}
    .mkString("{",",","}")
}
final case class JsString(value: String) extends JsValue {
  def stringify = "\"" + value.replaceAll("\\|\"","\\\\$1") + "\""
}
