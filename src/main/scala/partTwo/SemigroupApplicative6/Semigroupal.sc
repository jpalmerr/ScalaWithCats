/*
cats.Semigroupal is a type class that allows us to combine contexts
 */

// defn in cats
trait Semigroupal[F[_]] {
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
}

// While Semigroup allows us to join values, Semigroupal allows us to join contexts.

import cats.Semigroupal
import cats.instances.option._

Semigroupal[Option].product(Some(123), Some("abc")) // Some((123,abc))

/*
If BOTH parameters are instances of Some, we end up with a tuple of the values within.
IF EITHER parameter evaluates to None, the entire result is None:
 */

Semigroupal[Option].product(None, Some("abc")) // Option[(Nothing, String)] = None

// Joining 3 or more contexts

Semigroupal.tuple3(Option(1), Option(2), Option(3)) // Option[(Int, Int, Int)] = Some((1,2,3))

Semigroupal.map3(Option(1), Option(2), Option(3))(_ + _ + _) // Option[Int] = Some(6)

// Apply Syntax

// Cats provides apply syntax that provides a shorthand for the method described above.

import cats.syntax.apply._

// tuple

(Option(123), Option("abc")).tupled // Some((123,abc))
(Option(123), Option("abc"), Option(true)).tupled // Some((123,abc,true))

// mapN

case class Cat(name: String, born: Int, colour: String)

(
  Option("Garfield"),
  Option(1978),
  Option("Orange")
).mapN(Cat.apply) // Option[Cat] = Some(Cat(Garfield,1978,Orange))

// This syntax is type checked !! :)
