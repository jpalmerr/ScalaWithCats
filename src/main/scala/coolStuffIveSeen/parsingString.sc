import java.time.LocalDate
import scala.util.{Failure, Success, Try}

val input = "25-S-20220107"
// need 25 and 20220107 as a local date

val split = input.splitAt(2)

Try(split._1.toShort) match {
  case Failure(exception) => "blow up"
  case Success(value) => value
}

val splitArray = input.split("-S-")

val date = splitArray.drop(1).head

Try(splitArray.head.toShort) match {
  case Failure(exception) => "blow up"
  case Success(value) => value
}

val year = date.take(4).toInt

val month = date.drop(4).dropRight(2)

val day = date.drop(6)

"look below"

month.charAt(0).toString match {
  case "0" => month.charAt(1).toString.toInt
  case "1" => month.toInt
  case _ => "raise error"
}

day.charAt(0).toString match {
  case "0" => day.charAt(1).toString.toInt
  case "1" | "2" | "3" => day.toInt
  case _ => "raiseError"
}

"HERE"
val x = "31"
x.charAt(0).toString match {
  case "0" => day.charAt(1).toString.toInt
  case "1" | "2" | "3" => x.toInt
  case _ => "raiseError"
}
"^^^^"

LocalDate.of(2022, 1, 7)

def attempt(input: String): Unit = {
  val splitArray = input.split("-S-")
  val region: Short = splitArray.head.toShort
  val date = splitArray.drop(1).head
  val year = date.take(4).toInt
  val month = date.drop(4).dropRight(2)
  val day = date.drop(6)

  ()
}

"123456".split("-S-")

"01".toShort
