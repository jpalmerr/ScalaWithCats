import java.time.{LocalDateTime, ZonedDateTime}

val zdt = ZonedDateTime.parse("2022-01-01T02:00:00Z")

zdt.toLocalDate.toString

LocalDateTime.parse("2023-12-09T12:00:00.000")
