package week1.json

/**
  * Created by brett on 4/2/2017.
  */
object JsonPrintTest extends App {
  val simpleList = JsonArray(List(JsonValue("a string"), JsonValue(1.0), JsonValue(true)))
  println(simpleList.print)
  val simpleObject = JsonObject(List(
    JsonObjectCell("a", JsonArray(List(JsonValue("a string"), JsonValue(1.0), JsonValue(true)))),
    JsonObjectCell("b", JsonObject(List(
      JsonObjectCell("b.a", JsonValue(1.0)),
      JsonObjectCell("b.b", JsonValue(true)))
    )),
    JsonObjectCell("c", JsonValue(3))
  ))
  println(simpleObject.print)
}
