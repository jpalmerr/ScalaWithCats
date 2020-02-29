/*
Cats’ Foldable abstracts foldLeft and foldRight into a type class.

Instances of Foldable define these two methods and inherit a host of derived methods.
Cats provides out-of-the-box instances of Foldable for a handful of Scala data types:
 - List, Vector, Stream, and Option.
 */

import cats.{Eval, Foldable}
import cats.instances.list._ // for Foldable

val ints = List(1, 2, 3)
Foldable[List].foldLeft(ints, 0)(_ + _)

import cats.instances.option._ // for Foldable
val maybeInt = Option(123)

Foldable[Option].foldLeft(maybeInt, 10)(_ * _) // Int = 1230
Foldable[Option].foldLeft(maybeInt, 1)(_ + _) // Int = 124

/*
Using Foldable forces us to use stack safe operations
 */

import cats.instances.stream._ // for Foldable

def bigData: Stream[Int] = (1 to 100000).toStream
// bigData.foldRight(0L)(_ + _)
// java.lang.StackOverflowError ...
val eval: Eval[Long] =
  Foldable[Stream].
    foldRight(bigData, Eval.now(0L)) { (num, eval) =>
      eval.map(_ + num)
    }
eval.value // Long = 5000050000

/* Folding with Monoids

Foldable provides us with a host of useful methods defined on top of foldLeft.
 */

Foldable[Option].nonEmpty(Option(42)) // Boolean = true

Foldable[List].find(List(1, 2, 3))(_ % 2 == 0)
// Option[Int] = Some(2)

/* Cats provides two methods that make use of Monoids

- combineAll
- foldMap
 */

import cats.instances.int._ // for Monoid

Foldable[List].combineAll(List(1, 2, 3))
// Int = 6

import cats.instances.string._ // for Monoid
Foldable[List].foldMap(List(1, 2, 3))(_.toString)
// String = 123

import cats.instances.vector._ // for Monoid
val ints = List(Vector(1, 2, 3), Vector(4, 5, 6))

(Foldable[List] compose Foldable[Vector]).combineAll(ints)
// res15: Int = 21

// Syntax => don't have to set up with Foldable[Type]

import cats.syntax.foldable._ // for combineAll and foldMap

List(1, 2, 3 ,4).combineAll //Int 10

List(1, 2, 3, 4).foldMap(_.toString) // String = 1234