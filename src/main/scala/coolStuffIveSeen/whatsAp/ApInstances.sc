import cats.implicits._

/*
If you make an instance of Applicative then you need to supply an implementation of pure
which is exactly the same as pure found in Monads.
You also need to implement apply
 */

/*
trait Apply[F[_]] extends Functor[F] {
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
}

trait Applicative[F[_]] extends Apply[F] {
  def pure[A](x: A): F[A]
}

You can see that Apply extends Functor which means it has map.
Also it has the function ap which is, of course, what we're looking at.
Just like with map we are dealing with an effect type F, and a parameter F[A].
The difference is the function we want to apply (ff: F[A => B]) is also inside the effect
 */

// Instances

Option((a:Int) => a + 10).ap(Option(20)) // Some(30)
Option((a:Int) => a + 10).ap(None) // None
Option.empty[Int => Int].ap(Option(20)) // None
Option.empty[Int => Int].ap(Option.empty[Int]) // None

/*
When you apply the function,
if the ff argument is None then there’s nothing to apply and we get the result None
If there is a function
we extract it so that we have a pure function that we can apply to the effectful argument F[A]
Again, if that is empty we get None,
otherwise, we get the value f(a) which will be wrapped back up in the effect
 */

List((a:Int) => a + 1,
  (a:Int) => a - 10,
  (a:Int) => a + 22).ap(List(1,2,3))

// List[Int] = List(2, 3, 4, -9, -8, -7, 23, 24, 25)