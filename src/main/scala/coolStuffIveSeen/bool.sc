import java.time.{Instant, ZonedDateTime}

case class Thing(feature1: Int, feature2: String, time: ZonedDateTime)

val time1 = ZonedDateTime.now()
val time2 = ZonedDateTime.now().plusDays(1L)
val thing1 = Thing(1, "hello", time1)
val equalThing1 = Thing(1, "hello", time1)
val thing2 = Thing(2, "hello", time1)
val thing3 = Thing(1, "hello", time2)

val list: List[Thing] = List(thing1, equalThing1, thing2, thing3)

list.filterNot(thing => thing.equals(thing1))
