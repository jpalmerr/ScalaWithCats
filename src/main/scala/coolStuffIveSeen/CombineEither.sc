import cats.implicits._
val l1: Either[String, Int] = Either.left("error 1")
val l2: Either[String, Int] = Either.left("error 2")
val r3: Either[String, Int] = Either.right(3)
val r4: Either[String, Int] = Either.right(4)

l1 combine l2
// Either[String, Int] = Left(error 1)

l1 combine r3
// Either[String, Int] = Left(error 1)

r3 combine l1
// Either[String, Int] = Left(error 1)

r3 combine r4
// Either[String, Int] = Right(7)