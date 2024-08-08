import cats.data.{NonEmptyList, ValidatedNel}
import cats.syntax.all._
import cats.effect.IO
import cats.implicits.catsSyntaxEitherId

/*
Translated example of some changes I made at work
The goal was to capture all errors, rather than failing fast at the first error
 */

final case class MySuccessType(num: Int, bool: Boolean, anotherNum: Int)

val methodOne: IO[Either[String, Int]] = IO(1.asRight)
val methodTwo: IO[Either[String, Boolean]] = IO("error getting a boolean".asLeft)
val methodThree: IO[Either[String, Int]] = IO("error getting an int".asLeft)

val validatedRuleOne: IO[ValidatedNel[String, Int]] = methodOne.map(_.toValidatedNel)
val validatedRuleTwo: IO[ValidatedNel[String, Boolean]] = methodTwo.map(_.toValidatedNel)
val validatedRuleThree: IO[ValidatedNel[String, Int]] = methodThree.map(_.toValidatedNel)

def validate: IO[Either[NonEmptyList[String], MySuccessType]] =
  (validatedRuleOne, validatedRuleTwo, validatedRuleThree).mapN { (validatedOne, validatedTwo, validatedThree) =>
    (validatedOne, validatedTwo, validatedThree).mapN { (one, two, three) =>
      MySuccessType(one, two, three)
    }.toEither
}

val run: IO[Either[NonEmptyList[String], MySuccessType]] = for {
  result <- validate
} yield result

run.unsafeRunSync()
