import java.time.{LocalDate, ZoneId, ZonedDateTime}
import scala.collection.{MapView, View}

val londonZone: ZoneId = ZoneId.of("Europe/London")

case class Example (start: ZonedDateTime)
final case class ScheduleEntry(field: String)

def allMyLogic(examples: List[Example]): Seq[ScheduleEntry] = Seq(ScheduleEntry("EXAMPLE"))

final case class PlaylistSchedule(id: String, schedules: Seq[ScheduleEntry])

def genersysDate(zdt: ZonedDateTime): LocalDate = {
  // Base calculations on Europe / London time zone.
  val londonZdt = zdt.withZoneSameInstant(londonZone)
  if (londonZdt.getHour >= 0 && londonZdt.getHour < 6) {
    londonZdt.toLocalDate.minusDays(1)
  } else {
    londonZdt.toLocalDate
  }
}

val data = List(
  Example(ZonedDateTime.parse("2023-09-23T11:30:00+01:00")),
  Example(ZonedDateTime.parse("2023-09-23T12:30:00+01:00")),
  Example(ZonedDateTime.parse("2023-09-23T14:30:00+01:00")),
  Example(ZonedDateTime.parse("2023-09-24T11:30:00+01:00")),
  Example(ZonedDateTime.parse("2023-09-24T12:30:00+01:00")),
  Example(ZonedDateTime.parse("2023-09-24T14:30:00+01:00"))
)

val result: Map[LocalDate, List[Example]] = data.groupBy(localGenersysDate => genersysDate(localGenersysDate.start))
result


val scheduleEntriesPerDay: MapView[LocalDate, Seq[ScheduleEntry]] = result.view.mapValues { examplesPerDay =>
  allMyLogic(examplesPerDay)
}

val playListSchedules: List[PlaylistSchedule] = scheduleEntriesPerDay.map { myMap =>
  PlaylistSchedule(myMap._1.toString, myMap._2)
}.toList

playListSchedules


/**
 *
 *
 HashMap(
 2023-09-23 -> List(Example(2023-09-23T11:30+01:00), Example(2023-09-23T12:30+01:00), Example(2023-09-23T14:30+01:00)),
 2023-09-24 -> List(Example(2023-09-24T11:30+01:00), Example(2023-09-24T12:30+01:00), Example(2023-09-24T14:30+01:00)))
 *
 *
 */