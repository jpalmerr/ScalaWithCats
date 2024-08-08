import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, ZoneId, ZoneOffset, ZonedDateTime}
import java.time.format.DateTimeFormatter


//ZonedDateTime.

//import java.time.format.DateTimeFormatter.ISO_DATE_TIME
//import java.time.ZoneOffset.UTC
//
//val x = LocalDateTime.of(2022, 8, 10, 3, 2 )
//
//
//val localDateTime: LocalDateTime = LocalDateTime.now()
//OffsetDateTime.of(localDateTime, UTC)
//
//val urlFriendlyFormatter: DateTimeFormatter    = DateTimeFormatter.ofPattern("yyyyMMdd")
//LocalDateTime.parse("20220126", urlFriendlyFormatter)

//LocalDate.of(2022, 9, 3).format(ISO_DATE_TIME)
//
//ZonedDateTime.now(UTC)
//
//ZonedDateTime.parse("2022-01-01T12:00:00Z")

val instant = Instant.now().atZone(ZoneOffset.UTC)

import java.time.temporal.ChronoUnit._

val xx = instant.truncatedTo(DAYS)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYMMdd")

formatter.format(xx)
formatter.format(instant)

val startDate = Instant.parse("2022-12-30T09:01:02Z")

formatter.format(startDate.atZone(ZoneOffset.UTC))

val instant = Instant.parse("2024-01-30T09:01:02.00Z")

val zdt = instant.atZone(ZoneOffset.UTC)
  zdt
  .withHour(6)
  .withMinute(0)
  .withSecond(0)
  .withNano(0)
  .toInstant

zdt.withHour(23)
