case class Thing(factor: Boolean)

val thing1 = Thing(true)
val thing2 = Thing(false)
val thing3 = Thing(false)

val list = List(thing1, thing2, thing3)

list.partition(_.factor)

list.filter(_.factor)

case class Model(optional: Option[String])

val list1 = List(Model(Some("thing")), Model(None))

list1.filter(_.optional.isEmpty)

"".isN
