case class Channel(name: String, other: Int)

val itv1 = Channel("itv1", 1)
val itv2 = Channel("itv2", 2)
val itv3 = Channel("itv3", 3)
val citv = Channel("CITV", 6)

val channels: List[Channel] = List(itv1, itv2, itv3, citv)

channels.filter(_.name == "CITV")

"CITV".equalsIgnoreCase("citv")