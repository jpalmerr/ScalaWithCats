import java.time.ZonedDateTime

val middleTime = ZonedDateTime.now()
val earlyTime = middleTime.minusHours(1L)
val lateTime = middleTime.plusDays(1L)
case class Thing(time: ZonedDateTime)

val list = List(Thing(middleTime), Thing(lateTime), Thing(earlyTime))

list.maxBy(_.time).time
list.minBy(_.time).time