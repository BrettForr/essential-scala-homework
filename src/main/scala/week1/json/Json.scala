package week1.json

/**
  * Created by brett on 4/2/2017.
  */
sealed trait Json{
  def print: String = this match {
    case JsonValue(v) => v.toString // TODO: doesn't print strings with double quotes around it, as I would want
    case JsonArray(values) => s"[${values.map(_.print).mkString(", ")}]"
    case JsonObject(cells) => s"{${cells.map(c => s"${c.key}: ${c.value.print}").mkString(", ")}}"
  }
}
final case class JsonObject(cells: List[JsonObjectCell]) extends Json
final case class JsonArray(values: List[Json]) extends Json
final case class JsonValue[T : JsonableValue](value: T) extends Json

final case class JsonObjectCell(key: String, value: Json)



//sealed trait JsonValue[A] extends Json {
//  def value: A
//}
//final case class JsonDouble(value: Double) extends JsonValue[Double]
//final case class JsonInt(value: Int) extends JsonValue[Int]
//final case class JsonBoolean(value: Boolean) extends JsonValue[Boolean]
//final case class JsonString(value: String) extends JsonValue[String]
