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
"Hi " |+| "there"

import cats.instances.int._ // for Monoid
val intResult = 1 |+| 2 |+| Monoid[Int].empty
// intResult: Int = 3

import cats.instances.int._ // for Monoid
import cats.instances.option._ // for Monoid
import cats.instances.list._

val a = Option(2)
// a: Option[Int] = Some(2)

val b = Option(4)
// b: Option[Int] = Some(4)

val c = Option(6)
// b: Option[Int] = Some(6)

Monoid.combine(a, b) // Some(6) - easy to infer type

val list1: List[Option[Int]] = List(a, b)
val list2: List[Option[Int]] = List(a, c)

Monoid[List[Option[Int]]].combine(list1, list2).flatten // List(2, 4, 2, 6)

