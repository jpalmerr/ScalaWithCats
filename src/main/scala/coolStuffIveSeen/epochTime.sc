import java.time.Instant

final case class EpochTime(instant: Instant) extends AnyVal

object EpochTime {
  def apply(secondsSinceEpoch: Long): EpochTime =
    EpochTime(Instant.ofEpochSecond(secondsSinceEpoch))
}

// 2020-09-23T13:50:17.432698Z
EpochTime(1558364752.toLong)
EpochTime(1600868836.toLong)
EpochTime(1601473636.toLong)
EpochTime(1600869113.toLong) // canceled_at



// 2020-09-24T10:22:55.628Z
EpochTime(1558364752.toLong) // created