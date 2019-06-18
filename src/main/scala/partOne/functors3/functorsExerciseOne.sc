/*
Write a Functor for the following binary tree data type.
Verify that the code works as expected on instances of
 Branch and Leaf:
 */

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

// We recurse over the data structure,
// applying the function to every Leaf we find.

import cats.Functor
import cats.syntax.functor._ // for map

implicit val treeFunctor: Functor[Tree] =
  new Functor[Tree] {
    def map[A, B](tree: Tree[A])(func: A => B): Tree[B] =
      tree match {
        case Branch(left, right) =>
          Branch(map(left)(func), map(right)(func))
        case Leaf(value) =>
          Leaf(func(value))
      }
  }

// The compiler can find a Functor instance for Tree
// but not for Branch or Leaf.
// => build smart constructors

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)
  def leaf[A](value: A): Tree[A] =
    Leaf(value)
}

Tree.leaf(100).map(_ * 2)
// 100 leaves * 2 => Leaf(200)

Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)
// Branch(Leaf(20),Leaf(40))