import scala.language.higherKinds
import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap

def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
  for {
    x <- a
    y <- b
  } yield x*x + y*y

/*
 we saw this in previous example
 however it can't take in plain values
 be useful if we could...
 */

import cats.Id
sumSquare(3 : Id[Int], 4 : Id[Int])
// cats.Id[Int] = 25

// Id allows us to call our monadic method using plain values

val a = Monad[Id].pure(3)
// 3
val b = Monad[Id].flatMap(a)(_ + 1)
// 4