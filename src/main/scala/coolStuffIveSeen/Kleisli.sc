import com.typesafe.scalalogging.LazyLogging

object Kleisli extends LazyLogging {

  val simple_1: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_1 called with ${x}\n")
  }

  val simple_2: Boolean => (Boolean, String) = { (x: Boolean) =>
    (!x, s"simple_2 called with ${x}\n")
  }

  // regular function composition
  def compose[A, B, C](f: A => B, g: B => C): A => C = { (x: A) =>
  {
    val p1 = f(x)
    val p2 = g(p1)
    p2
  }
  }

  // we want composition for tuples
  def composeT[A](f: A => (A, String),
                  g: A => (A, String)): A => (A, String) = { (x: A) =>
  {
    val p1 = f(x)
    val p2 = g(p1._1)
    (p2._1, p1._2 + p2._2) //string appending happens inside the composition, I'm combining results of 2 functions, this is my way of composing functions
  }
  }

  def composed: Boolean => (Boolean, String) = composeT(simple_1, simple_2)

  logger.info(s"Result: ${composed.apply(true)}")

  // oh, and if we have associativity, can we make an identity?!

  def id[A](a: A): (A, String) = (a, "") // yep!
}
