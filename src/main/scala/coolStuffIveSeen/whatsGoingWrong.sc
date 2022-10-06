import cats.data.{NonEmptyList, ValidatedNel}
import cats.implicits._

import scala.util.{Failure, Success, Try}

final case class ParsedId(uniqueId: Int, scteRegionId: Short, breakNumber: Short)

def attemptParse(uniqueId: Int): Either[NonEmptyList[IllegalStateException], ParsedId] = {
  val BBBBBBBB = uniqueId >>> 6
  val SSSSSS = uniqueId - (BBBBBBBB << 6)

  def validateShort(int: Int): ValidatedNel[IllegalStateException, Short] = {
    Try(int.toShort) match {
      case Failure(exception) => (new IllegalStateException("Failed to convert parsed response to short", exception)).invalidNel
      case Success(value) => value.valid
    }
  }

  def validateSCTERegion(scte: Int): ValidatedNel[IllegalStateException, Unit] = {
    if (scte < 32 || scte > 63) {
      new IllegalStateException(s"SCTE region id was not in 6 bit range 32 - 63 for uniqueId: $uniqueId with binary ${uniqueId.toBinaryString}").invalidNel
    } else {
      ().valid
    }
  }

  def validateBreakNumber(breakNumber: Int): ValidatedNel[IllegalStateException, Unit] = {
    if (breakNumber > 255) {
      new IllegalStateException(s"Break number was not in 8 bit range 0 - 255 for uniqueId: $uniqueId with binary ${uniqueId.toBinaryString}").invalidNel
    } else {
      ().valid
    }
  }

  (validateShort(SSSSSS), validateShort(BBBBBBBB), validateSCTERegion(SSSSSS), validateBreakNumber(BBBBBBBB)).mapN { case (scte, breakNum, _, _) =>
    ParsedId(uniqueId, scte, breakNum)
  }.toEither
}

def countBinary(binaryString: String, count: Int = 0, multiple: Int = 1): Int = {
  if (binaryString.isEmpty) {
    count
  }
  else {
    binaryString.charAt(binaryString.length - 1).toString match {
      case "1" =>
        if (multiple === 1) {
          countBinary(binaryString.splitAt(binaryString.length - 1)._1, count + (1 * multiple), multiple + 1)
        } else {
          countBinary(binaryString.splitAt(binaryString.length - 1)._1, count + (1 * multiple), multiple * 2)
        }

      case _ =>
        countBinary(binaryString.splitAt(binaryString.length - 1)._1, count, multiple * 2)
    }
  }
}

val uniqueId = 15206

val BBBBBBBB = uniqueId >>> 6
println(BBBBBBBB.toBinaryString)
val SSSSSS = uniqueId - (BBBBBBBB << 6)
println(SSSSSS.toBinaryString)

"11101101100110".length

countBinary("11101101100110")

31.toBinaryString
31.toBinaryString.length
256.toBinaryString
256.toBinaryString.length

"10000000011111"
countBinary("10000000011111")

println("---------------")
countBinary("10000000011111")

countBinary("0011111100000000")

16128 >>> 6
252.toBinaryString
16128 - (252 << 6)
(16128 - (252 << 6)).toBinaryString
countBinary("11111100")

256.toBinaryString

val x = 256 >>> 6
256 - (x << 6)

256.toBinaryString
256.toBinaryString.length
countBinary("100000000")
"00 00001000 000000"
countBinary("00001000")

16150.toBinaryString
16150 >>> 6
16151 >>> 6
16500 >>> 6

val y = 20865 >>> 6
20865 - (y << 6)
