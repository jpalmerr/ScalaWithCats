// We can use pure to construct instances of a monad.

import cats.instances.option._ // for Monad
import cats.instances.list._ // for Monad
import cats.syntax.applicative._ // for pure

1.pure[Option]
// res4: Option[Int] = Some(1)
1.pure[List]
// res5: List[Int] = List(1)

/*
It’s difficult to demonstrate the flatMap and map methods
directly on Scala monads like Option and List,
because they define their own explicit versions
of those methods.
 */

import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
import scala.language.higherKinds


def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
  a.flatMap(x => b.map(y => x*x + y*y))


sumSquare(Option(3), Option(4))
// Option[Int] = Some(25)

sumSquare(List(1, 2, 3), List(4, 5))
// List[Int] = List(17, 26, 20, 29, 25, 34)
// List(1^2 + 4^2, 1^2 + 5^2, 2^2 + 4^2, 2^2 + 5^2, 3^2 + 4^4, 3^3 + 5^5)

/*
We can rewrite this code using for comprehensions.
The compiler will “do the right thing” by rewriting our
comprehension in terms of flatMap and map and
inserting the correct implicit conversions to use our Monad:
 */

def sumSquare2[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
  for {
    x <- a
    y <- b
  } yield x*x + y*y

sumSquare2(Option(3), Option(4))

sumSquare2(List(1, 2, 3), List(4, 5))
