import cats.syntax.all._

List(Option(1), Option(2)).sequence // Some(List(1, 2))
List(Option(1), None).sequence // None

Option(List(1, 2)).sequence // List(Some(1), Some(2))

List(1.asRight[String], 2.asRight[String]).sequence // Right(List(1, 2))
List(1.asRight[String], "err".asLeft[Int]).sequence // Left("err") - fail fast

/**
 * So sequence turns an F[G[A]] into G[F[A]] but exactly how depends on the F and G.
 */
