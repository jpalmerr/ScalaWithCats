import cats.data.ValidatedNel
import cats.implicits.catsSyntaxValidatedId

def validateSCTERegion(scte: Int): ValidatedNel[IllegalStateException, Unit] = {
  if (scte < 32 || scte > 63) {
    new IllegalStateException().invalidNel
  } else {
    ().valid
  }
}

validateSCTERegion(0)