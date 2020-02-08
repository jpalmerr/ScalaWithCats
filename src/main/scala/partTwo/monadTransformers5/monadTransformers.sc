/*
Cats provides transformers for many monads, each named with a T suffix:

EitherT composes Either with other monads, OptionT composes Option,
and so on.
 */

// use OptionT to compose List and Option

import cats.data.OptionT

type ListOption[A] = OptionT[List, A]

// creating instances using OptionT constructor, or (preferred) pure

import cats.instances.list._ // for Monad
import cats.syntax.applicative._ // for pure

val result1: ListOption[Int] = OptionT(List(Option(10)))
// result1: ListOption[Int] = OptionT(List(Some(10)))
val result2: ListOption[Int] = 32.pure[ListOption]
// result2: ListOption[Int] = OptionT(List(Some(32)))

// the map and flatMap methods combine the corresponding methods of
// List and Option into single operations

result1.flatMap {x: Int =>
  result2.map { y: Int =>
    x + y
  }
}
// OptionT(List(Some(42)))