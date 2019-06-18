// type class, instances, and syntax

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._ // for Monad

val opt1 = Monad[Option].pure(3)
// Some(3)
val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
// Some(5)
val opt3 = Monad[Option].map(opt2)(a => 100 * a)
// Some(500)

val list1 = Monad[List].pure(3)
// List(3)
val list2 = Monad[List].pure(3, 2, 1)
// List((3,2,1))

val list3 = Monad[List].
  flatMap(List(1, 2, 3))(a => List(a, a*10))
// List(1, 10, 2, 20, 3, 30)

val list4 = Monad[List].map(list3)(a => a + 123)
// List(124, 133, 125, 143, 126, 153)

/*
 Monad provides many other methods,
 including all of the methods from Functor.
 */

// Cats provides instances for all the monads in the standard library

import cats.instances.option._ // for Monad

Monad[Option].flatMap(Option(1))(a => Option(a*2))
// res0: Option[Int] = Some(2)

import cats.instances.list._ // for Monad

Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10))
// res1: List[Int] = List(1, 10, 2, 20, 3, 30)

import cats.instances.vector._ // for Monad

Monad[Vector].flatMap(Vector(1, 2, 3))(a => Vector(a, a*10))
// res2: Vector[Int] = Vector(1, 10, 2, 20, 3, 30)