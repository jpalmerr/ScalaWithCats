import io.circe.{Decoder, Json}
import io.circe.syntax._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

val validDateFormat = "yyyyMMddHHmm"
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(validDateFormat)

implicit val decoder: Decoder[LocalDateTime] = Decoder.decodeLocalDateTimeWithFormatter(formatter).map(_.truncatedTo(ChronoUnit.SECONDS))


Json.obj("start" -> "202312250815".asJson).as[LocalDateTime]


// 2023 12 25 1415
// 2023 12 25 08 15