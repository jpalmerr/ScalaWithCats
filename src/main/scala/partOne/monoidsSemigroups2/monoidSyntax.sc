/*
Cats provides syntax for the combine method in the form of the |+| operator.
Because combine technically comes from Semigroup,
 we access the syntax by importing from cats.syntax.semigroup:
 */

import cats.kernel.Monoid
import cats.syntax.semigroup._ // for |+|
import cats.instances.string._ // for instances of string

// notice identity not changing result

val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
// stringResult: String = Hi there

import cats.instances.int._ // for Monoid
val intResult = 1 |+| 2 |+| Monoid[Int].empty
// intResult: Int = 3