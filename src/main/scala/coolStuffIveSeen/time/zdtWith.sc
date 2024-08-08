import java.time.ZonedDateTime

ZonedDateTime.now().withHour(5).withMinute(59).withSecond(59)

val one = ZonedDateTime.now()
val two = one.plusDays(10L)
val three = one.minusDays(10L)

List(one, two, three).sorted

val example = ZonedDateTime.parse("2022-01-01T02:00:00Z")

(0 <= example.getHour) && (example.getHour < 6)
