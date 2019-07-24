import cats.data.OptionT

/*
All of these monad transformers follow the same convention.
The transformer itself represents the inner monad in a stack,
while the first type parameter specifies the outer monad.
*/

type ListOption[A] = OptionT[List, A]
// consider how the result is effectively:
// List[Option[A]]
// we build monad stacks from inside out

// we often have to define type aliases for parameters

/*
For example, suppose we want to wrap Either around Option.
Option is the innermost type so we want to use the OptionT monad transformer.
We need to use Either as the first type parameter.
However, Either itself has two type parameters and monads only have one.
We need a type alias to convert the type constructor to the correct shape:
 */

// Alias Either to a type constructor with one parameter:
type ErrorOr[A] = Either[String, A]
// Build our final monad stack using OptionT:
type ErrorOrOption[A] = OptionT[ErrorOr, A]

/*
ErrorOrOption is a monad, just like ListOption.
We can use pure, map, and flatMap
as usual to create and transform instances:
*/

import cats.instances.either._ // for Monad
import cats.syntax.applicative._ // for pure

val a = 10.pure[ErrorOrOption]

val b = 32.pure[ErrorOrOption]

val c = a.flatMap(x => b.map(y => x + y))
