import cats.implicits._
import cats.data.EitherT

val eitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Left("abc"), Right(123)))

/**
 * MonadT[F, A, B]
 */

/**
 * semiFlatMap[D]
 * takes: B => F[D]
 * returns: MonadT[F, A, D]
 */

eitherT.semiflatMap(int => List(int.toFloat))
// EitherT(List(Left(abc), Right(123.0)))


/**
 * leftSemiFlatMap[D]
 * takes: A => F[D]
 * returns: MonadT[F, B, D]
 */

eitherT.leftSemiflatMap(string => List(string.capitalize))
// EitherT[List,String,Int] = EitherT(List(Left(Abc), Right(123)))

/**
 * biSemiFlatMap
 *  combine leftSemiFlatMap and semiFlatmap together
 */

eitherT.biSemiflatMap(string => List(string.capitalize), int => List(int.toFloat))
// EitherT[List,String,Float] = EitherT(List(Left(Abc), Right(123.0)))

// useful for logging!
