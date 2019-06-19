// in scala 2.12, Either became right biased
// => a monad

val either1: Either[String, Int] = Right(10)
val either2: Either[String, Int] = Right(32)

for {
  a <- either1
  b <- either2
} yield a + b // right 42

// creating instances

/*
In addition to creating instances of Left and Right directly, we
can also import the asLeft and asRight extension methods from
cats.syntax.either
 */

import cats.syntax.either._ // for asRight

val a = 3.asRight[String]
// a: Either[String,Int] = Right(3)
val b = 4.asRight[String]
// b: Either[String,Int] = Right(4)
for {
  x <- a
  y <- b
} yield x*x + y*y

// asRight

def countPositive(nums: List[Int]):Either[String, Int] =
  nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
    if(num > 0) {
      accumulator.map(_ + 1) // notice use of map rather than += 1
    } else {
      Left("Negative. Stopping!")
    }
  }
countPositive(List(1, 2, 3))
// Right(3)
countPositive(List(1, -2, 3))
// Left(Negative. Stopping!)

// Transforming Eithers

// cats.syntax.either also adds some useful methods for instances of Either.

// getOrElse
"Error".asLeft[Int].getOrElse(0) // 0
"Error".asRight[Int].getOrElse(0) // Error
2.asRight[Int].getOrElse(0) // 2

// ensure
(-1).asRight[String].ensure("Must be non-negative!")(_ > 0)