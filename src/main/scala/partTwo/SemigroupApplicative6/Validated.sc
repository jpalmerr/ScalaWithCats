import cats.data.Validated

/*
By now we are familiar with the fail-fast error handling behaviour of Either.
Furthermore, because Either is a monad, we know that the semantics of
product are the same as those for flatMap.

In fact, it is impossible for to design a monadic data type that implements
error accumulating semantics without breaking the consistency of these two methods.

Fortunately, Cats provides a data type called Validated that has an instance
of Semigroupal but no instance of Monad.

The implementation of product is therefore free to accumulate errors:
 */

val v = Validated.Valid(123)
// cats.data.Validated.Valid[Int] = Valid(123)
val i = Validated.Invalid(List("Badness"))
// cats.data.Validated.Invalid[List[String]] = Invalid(List(Badness))

/*
However, it is often easier to use the valid and invalid smart constructors,
which widen the return type to Validated:
 */

val valid = Validated.valid[List[String], Int](123)
// cats.data.Validated[List[String],Int] = Valid(123)
val invalid = Validated.invalid[List[String], Int](List("Badness"))
// cats.data.Validated[List[String],Int] = Invalid(List(Badness))

/*
As a third option we can import the valid and invalid extension methods
from cats.syntax.validated:
*/

import cats.syntax.validated._ // for valid and invalid

123.valid[List[String]] // cats.data.Validated[List[String],Int] = Valid(123)

List("Badness").invalid[Int] // cats.data.Validated[List[String],Int] = Invalid(List(Badness))
