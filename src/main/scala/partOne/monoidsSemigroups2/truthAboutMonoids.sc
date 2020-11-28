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

// four monoids for boolean

// or

implicit val orMonoid: Monoid[Boolean] =
  new Monoid[Boolean] {
    override def empty = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

// and

implicit val andMonoid: Monoid[Boolean] =
  new Monoid[Boolean] {
    override def empty = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

// exclusive or: outputs true only when inputs differ

implicit val exclusiveOr: Monoid[Boolean] =
  new Monoid[Boolean] {
    override def empty = false

    override def combine(x: Boolean, y: Boolean): Boolean =
      (x && !y) || (y && !x)
  }

// exclusive nor: either one or neither

implicit val exclusiveNor: Monoid[Boolean] =
  new Monoid[Boolean] {
    override def empty = true

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x || y) && (x || !y)
  }