trait Store[F[_]] {
  def dbCall1(string: String): F[Unit]
  def dbCall2(string: String): F[Unit]
}

trait Handler[F[_]] {
  def handle: F[Unit]
}

/*
I need two handlers, one using dbCall1, one using dbCall2
The logic inside `def handle` is EXACTLY the same, aside from using a different db call

Can this be modelled without passing in the db call as a method parameter? At least not as a signature definition e.g. dbCall: String => F[Unit]
 */

