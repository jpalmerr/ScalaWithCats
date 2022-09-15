import java.time.{LocalDate, LocalDateTime, OffsetDateTime, ZoneId, ZonedDateTime}
import java.time.format.DateTimeFormatter


//ZonedDateTime.

import java.time.format.DateTimeFormatter.ISO_DATE_TIME
import java.time.ZoneOffset.UTC

val x = LocalDateTime.of(2022, 8, 10, 3, 2 )


val localDateTime: LocalDateTime = LocalDateTime.now()
OffsetDateTime.of(localDateTime, UTC)

//val urlFriendlyFormatter: DateTimeFormatter    = DateTimeFormatter.ofPattern("yyyyMMdd")
//LocalDateTime.parse("2022-01-26", urlFriendlyFormatter)

LocalDate.of(2022, 9, 3).format(ISO_DATE_TIME)

ZonedDateTime.now(UTC)

ZonedDateTime.parse("2022-01-01T12:00:00Z")