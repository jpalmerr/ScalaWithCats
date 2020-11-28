trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] =
    monoid
}

implicit def unionMonoid[A]: Monoid[Set[A]] =
  new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

val intSetMonoid = Monoid[Set[Int]]
val strSetMonoid = Monoid[Set[String]]

intSetMonoid.combine(Set(1, 2), Set(2, 4))
intSetMonoid.combine(Set(1, 2), Set(3, 4))

strSetMonoid.combine(Set("Hello"), Set("World"))

/*
Set intersecti􏰀on forms a semigroup,
but doesn’t form a monoid because it has no identity element
 */

implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
  def combine(a: Set[A], b: Set[A]): Set[A] =
    a intersect b
}

//symmetric difference

/*

implicit def symDiffMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
  override def empty = Set.empty[A]

  override def combine(x: Set[A], y: Set[A]): Set[A] =
    (x diff y) union (y diff x)
}

 */


