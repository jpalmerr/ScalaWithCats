import java.time.format.DateTimeFormatter.ISO_DATE
import java.time.{Duration, Instant, LocalDate, ZonedDateTime}
import java.time.temporal.{ChronoField, TemporalField}
import scala.concurrent.duration.FiniteDuration

val instant: Instant = Instant.ofEpochSecond(180)

import java.time.Duration

Duration.ofSeconds(100)

Duration.parse("PT3M030S")

LocalDate.now().format(ISO_DATE)

ZonedDateTime.parse("2022-01-01T23:45:00Z").plusDays(1L)

Duration.parse("PT3M030S").toSeconds

val d: Duration = Duration.parse("PT3M030S")

