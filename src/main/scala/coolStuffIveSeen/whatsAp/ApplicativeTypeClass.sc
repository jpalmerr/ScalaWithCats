import cats.Applicative
import cats.implicits._

sealed trait Tree[+A]
case object Leaf extends Tree[Nothing]
case class Node[A](left: Tree[A], a: A, right: Tree[A]) extends Tree[A]

def treeTraverse[A, B, F[_]](f: A => F[B], fs: Tree[A])
                            (implicit app: Applicative[F]): F[Tree[B]] = {
  fs match {
    case Leaf =>
      app.pure(Leaf)
    case Node(left, a, right) =>
      val w1 = app.pure((l: Tree[B]) =>
        (v: B) =>
          (r: Tree[B]) => Node(l,v,r))
      val w2 = w1.ap(treeTraverse(f,left))
      val w3 = w2.ap(f(a))
      w3.ap(treeTraverse(f,right))
  }
}

val tree1 = Node(Leaf, 10, Node(Leaf, 5, Node(Leaf, 10, Leaf)))
println("treeTraverse: " + treeTraverse((n: Int) => Option(n + 1), tree1))