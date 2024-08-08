import cats.data.{NonEmptyList, ValidatedNel}
import cats.implicits.{catsSyntaxTuple4Semigroupal, catsSyntaxValidatedId}

import scala.util.{Failure, Success, Try}

final case class ParsedId(uniqueId: Int, scteRegionId: Short, breakNumber: Short)

def attemptParse(uniqueId: Int): Either[NonEmptyList[IllegalStateException], ParsedId] = {
  val BBBBBBBB = uniqueId >>> 6
  println(BBBBBBBB.toBinaryString)
  val SSSSSS = uniqueId - (BBBBBBBB << 6)
  println(SSSSSS.toBinaryString)

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

//val x = 4095
//x.toBinaryString
//attemptParse(4095)

attemptParse(63)