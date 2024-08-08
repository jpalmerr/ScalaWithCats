case class Thing(maybe: Option[String])
val thing1 = Thing(Some("lose me"))
val thing2 = Thing(Some("keep me"))
val thing3 = Thing(None) // lose me

val myList = List(thing1, thing2, thing3)

myList.filterNot {x =>
  x.maybe.forall(s => s == "lose me")
}