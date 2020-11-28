import java.time.{Instant, LocalDateTime, ZoneOffset}
import java.time.temporal.ChronoUnit

val in30Days = LocalDateTime.now().plus(30, ChronoUnit.DAYS).toInstant(ZoneOffset.UTC)
Instant.now()
LocalDateTime.now()

Instant.now().plus(30, ChronoUnit.DAYS)

case class Wrapper(value: String)

def getValue(wrapper: Option[Wrapper]): Option[String] = {
  wrapper.map(_.value)
}

getValue(Some(Wrapper("VALUE")))
getValue(None)