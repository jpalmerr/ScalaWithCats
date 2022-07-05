case class Channel(name: String, id: String)

val orderedIds: List[String] = List("itv1", "itv2", "itv3")
val randomChannels: List[Channel] = List(Channel("two", "itv2"), Channel("three", "itv3"), Channel("one", "itv1"))

val x: List[Channel] = randomChannels.sortWith { (a, b) =>
  orderedIds.indexOf(a.id) < orderedIds.indexOf(b.id)
}