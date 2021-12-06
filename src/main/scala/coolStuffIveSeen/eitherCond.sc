import scala.util.{Either, Left, Right}

/**
def cond[A, B](test: Boolean, right: => B, left: => A): Either[A, B] =
  if (test) Right(right) else Left(left)
 */

val myTest = true
case class Failure(msg: String)
case class Success(msg: String)

Either.cond(
  test = myTest,
  left = Failure("error"),
  right = Success("woohoo")
)
// Either[Failure,Success]