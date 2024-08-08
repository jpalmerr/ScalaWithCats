import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit.MINUTES

val d = Duration.apply("30 mins")

d.toMinutes


Duration.apply(100L, MINUTES)

