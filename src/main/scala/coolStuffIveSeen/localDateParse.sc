import java.time.LocalDate
import java.time.ZoneOffset.UTC
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
val date: String = "20220630"

//convert String to LocalDate
val yyyyMMdd: LocalDate = LocalDate.parse(date, formatter)

yyyyMMdd

yyyyMMdd.toString

val fm = DateTimeFormatter.ISO_LOCAL_DATE.withZone(UTC)

yyyyMMdd.format(fm).toCharArray.filterNot(_ == '-').mkString


yyyyMMdd.format(formatter).toLong
yyyyMMdd.format(formatter).toLong.toString

