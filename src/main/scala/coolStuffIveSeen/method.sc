import cats.data.ValidatedNel
import cats.implicits.catsSyntaxValidatedId
import cats.effect.IO

def validateSCTERegion(scte: Int): ValidatedNel[IllegalStateException, Unit] = {
  if (scte < 32 || scte > 63) {
    new IllegalStateException().invalidNel
  } else {
    ().valid
  }
}

validateSCTERegion(0)

def thing(): IO[Unit] = IO.raiseError[Unit](new Error("error"))

thing() match {
  case _: IO() => IO.unit.valid
  case e => new Error("123").invalid
}