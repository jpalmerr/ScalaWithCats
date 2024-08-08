import java.time.{Duration, Instant}
import java.time.temporal.ChronoField.{HOUR_OF_DAY, MINUTE_OF_HOUR, NANO_OF_SECOND, SECOND_OF_MINUTE}
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.HOURS

//val now = Instant.now()

//now.`with`(HOUR_OF_DAY, 6).`with`(MINUTE_OF_HOUR, 0).`with`(SECOND_OF_MINUTE, 0).`with`(NANO_OF_SECOND, 0)



val instant = Instant.now();

// Calculate the start of the day
val startOfDay = instant.truncatedTo(ChronoUnit.DAYS);

// Calculate the duration to add to reach 6 AM
val sixHoursInSeconds = 6 * 60 * 60
val duration = Duration.ofSeconds(sixHoursInSeconds);

// Update the Instant to 6 AM
val updatedInstant = startOfDay.plus(duration);

"Original Instant: " + instant
"Updated Instant: " + updatedInstant


instant.truncatedTo(ChronoUnit.DAYS).plusSeconds(sixHoursInSeconds).plus(20, ChronoUnit.MINUTES)