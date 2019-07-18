/*
For convenience, Cats provides a way of creating Writers specifying only the
log or the result. If we only have a result we can use the standard pure syntax.
To do this we must have a Monoid[W] in scope so Cats knows how to produce
an empty log:
 */

import cats.data.Writer
import cats.instances.vector._ // for Monoid
import cats.syntax.applicative._ // for pure

type Logged[A] = Writer[Vector[String], A]
123.pure[Logged] // Logged[Int] = WriterT((Vector(),123))

/*
If we have a log and no result we can create a
Writer[Unit] using the tell syntax
 */

import cats.syntax.writer._ // for tell
Vector("msg1", "msg2", "msg3").tell

// If we have both a result and a log:

val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
// WriterT((Vector(msg1, msg2, msg3),123))

val b = 123.writer(Vector("msg1", "msg2", "msg3"))
// WriterT((Vector(msg1, msg2, msg3),123))

/*
we can extract the result and log
or extract both at the same time
 */

val aResult: Int =
  a.value
// aResult: Int = 123

val aLog: Vector[String] =
  a.written
// Vector(msg1, msg2, msg3)

val (log, result) = b.run
/*
log: scala.collection.immutable.Vector[String] = Vector(msg1, msg2, msg3)
result: Int = 123
 */

// Composing Writers

// transform the result

val writer1 = for {
  a <- 10.pure[Logged]
  _ <- Vector("a", "b", "c").tell
  b <- 32.writer(Vector("x", "y", "z"))
} yield a + b
 // WriterT((Vector(a, b, c, x, y, z),42))

writer1.run
// (Vector(a, b, c, x, y, z),42)

// transform the log

// using mapWritten

val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
writer2.run
//
//
//(Vector(A, B, C, X, Y, Z),42)

// transform both

/*
using bimap or mapBoth
bimap takes two function parameters, one for the log and one for the result.
mapBoth takes a single function that accepts two parameters:
 */

val writer3 = writer1.bimap(
  log => log.map(_.toUpperCase),
  res => res * 100
)
writer3.run
// (Vector(A, B, C, X, Y, Z),4200)

val writer4 = writer1.mapBoth { (log, res) =>
  val log2 = log.map(_ + "!")
  val res2 = res * 1000
  (log2, res2)
}
writer4.run
// (Vector(a!, b!, c!, x!, y!, z!),42000)

//  clear the log
val writer5 = writer1.reset
writer5.run
// (Vector(),42)

// swap
val writer6 = writer1.swap
writer6.run
// (42,Vector(a, b, c, x, y, z))
