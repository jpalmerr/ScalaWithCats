import cats.implicits._
import cats.data.Validated
import Validated.{Invalid, Valid}
import cats.Apply

type ErrOr[A] = Validated[String, A]

val validInt: ErrOr[Int] = Valid(3)
val validBool: ErrOr[Boolean] = Valid(true)
val invalidInt: ErrOr[Int] = Invalid("Invalid int")
val invalidBool: ErrOr[Boolean] = Invalid("Invalid boolean")

Apply[ErrOr].productL(validInt)(validBool)
// Valid(3)

Apply[ErrOr].productL(invalidInt)(validBool)
// Invalid(Invalid int)

Apply[ErrOr].productL(validInt)(invalidBool)
// invalidInt: ErrOr[Int] = Invalid(Invalid int)

Apply[ErrOr].productL(invalidInt)(invalidBool)
// Invalid(Invalid intInvalid boolean)

/**
method I regularly use in http clients
via syntax
"<*"

particularly useful to log after a computation you want to return
(e.g. log after the successful decoding but still return decoding model)
 */