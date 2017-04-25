import week3.jsonHW.{JsObject, JsString}

val test = JsObject(Map("foo" -> JsString("a"), "bar" -> JsString("b"), "baz" -> JsString("c")))
test.stringify