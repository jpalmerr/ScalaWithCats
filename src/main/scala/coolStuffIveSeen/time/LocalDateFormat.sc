import java.time.LocalDate
import java.time.format.DateTimeFormatter

val now = LocalDate.now()
val extendedDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

now.format(extendedDateTimeFormatter)