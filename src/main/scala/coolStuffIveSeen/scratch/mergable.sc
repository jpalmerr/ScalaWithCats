trait Mergeable[T] {
  def canMerge(a: T, b: T): Boolean
  def merge(a: T, b: T): T
}

implicit val merge: Mergeable[Int] = new Mergeable[Int] {
  override def canMerge(a: Int, b: Int): Boolean = a == b

  override def merge(a: Int, b: Int): Int = a + b
}

def merge[T](l: Seq[T])(implicit val mergable: Mergeable[T]): Seq[T] = {
  def loop(l: Seq[T], acc: Seq[T]): Seq[T] =
    l match
  case Nil =>
    acc
  case x :: Nil =>
  acc :+ x
  case x :: y :: xs =>
  if (mergeable.canMerge(x, y))
    loop(xs, acc :+ mergeable.merge(x, y))
  else
    loop(y :: xs, acc :+ x)

  loop(l, Seq.empty)
}

assert(merge(Seq.empty) == Seq.empty)
assert(merge(Seq(1)) == Seq(1))
assert(merge(Seq(1, 2)) == Seq(1, 2))
assert(merge(Seq(1, 1)) == Seq(2))
assert(merge(Seq(1, 1, 1)) == Seq(2, 1))
assert(merge(Seq(1, 1, 2, 3, 3)) == Seq(2, 2, 6))

println("Yesssirrrr!")