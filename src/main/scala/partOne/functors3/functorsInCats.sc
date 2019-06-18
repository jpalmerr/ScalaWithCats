/*
Let’s look at the implementation of functors in Cats.
We’ll examine the aspects we did for monoids:
the type class, the instances, and the syntax.
 */

// Functor Type Class

/*
We obtain instances using the
standard Functor.apply method on the companion object.
 */

import scala.language.higherKinds
import cats.Functor
import cats.instances.list._ // for Functor
import cats.instances.option._ // for Functor

val list1 = List(1, 2, 3)
// list1: List[Int] = List(1, 2, 3)
val list2 = Functor[List].map(list1)(_ * 2)
// List(2, 4, 6)

val option1 = Option(123)
// Option[Int] = Some(123)
val option2 = Functor[Option].map(option1)(_.toString)
// Option[Int] = Some(123)

// Functor also provides the lift method
/*
converts a function of type A => B
to one that operates over a functor
type F[A] => F[B]
 */

val func = (x: Int) => x + 1
// Int => Int

val liftedFunc = Functor[Option].lift(func)
// Option[Int] => Option[Int]

liftedFunc(Option(1))
// Some(2)

// Functor Syntax

/*
We can write a method that
applies an equation to a number
no matter what functor context it’s in
 */

import cats.syntax.functor._ // for map

def doMath[F[_]](start: F[Int])
                (implicit functor: Functor[F]): F[Int] =
  start.map(n => n + 1 * 2) // ... n + 2

import cats.instances.option._ // for Functor
import cats.instances.list._ // for Functor

doMath(Option(20))
// Some(22)
doMath(List(1, 2, 3))
// List(3, 4, 5)
