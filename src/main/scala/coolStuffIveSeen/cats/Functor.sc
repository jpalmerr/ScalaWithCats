/*
We have two categories, A and B.
In Category A, we have two objects: a and b with morphism f

Our functor is:
 a mapping of objects a and b to Fa and Fb
 and mapping of morphisms: in this case single morphism f to Ff

 If you can compose two morphisms in one Category you can compose them in another when using a Functor

 F(g · f) = Fg · Ff

 Functor must also preserve identity.

 Fid a = Fa
 */

/*
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def lift[A, B](f: A => B): F[A] => F[B] =
    fa => map(fa)(f)
}
 */


/*
The most common example for the Functor you will possibly see is about mapping on nested data types.

map dot map !!!:D
 */

val lst = List(Some(1), None, Some(2))
val doubled = lst.map(_.map(_ * 2))  //List(Some(2), None, Some(4))

import cats.Functor
import cats.implicits._

val doubledUsingFunctor = Functor[List].compose[Option].map(lst)(_ * 2)
println(doubledUsingFunctor)

val complexProduct = List(
  List(
    Some(10),
    None
  ),
  List(
    Some(22)
  )
)

val doubleCP = Functor[List].compose[List].compose[Option].map(complexProduct)(_ * 2)  //List(List(Some(20), None), List(Some(44)))