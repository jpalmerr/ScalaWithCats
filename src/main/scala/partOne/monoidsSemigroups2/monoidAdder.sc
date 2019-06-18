// adder

def add(items: List[Int]): Int =
  items.foldLeft(0)(_ + _)

// or in cats

import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.syntax.semigroup._ // for |+|
def add2(items: List[Int]): Int =
  items.foldLeft(Monoid[Int].empty)(_ |+| _)

val list = List(1, 2, 3, 4)
add(list)
add2(list)

// however not a compelling case to use cats yet

/*
People now want to add List[Option[Int]].
Change add so this is possible.
 */

// Now there is a use case for Monoids.
// We need a single method that adds Ints and instances of Option[Int].
import cats.instances.option._

def add3[A: Monoid](items: List[A]): A =
  items.foldLeft(Monoid[A].empty)(_ |+| _)

add3(list)
val optionList = List(Some(1), None, Some(2), None, Some(3))
add3(optionList)

// note won't compile if all some

// SuperAdder is entering the POS (point-of-sale) market.
// Now we want to add up Orders:

case class Order(totalCost: Double, quantity: Double)

// we need to release this code quick => don't change add!

implicit val monoid: Monoid[Order] = new Monoid[Order] {
  def combine(o1: Order, o2: Order) =
    Order(
      o1.totalCost + o2.totalCost,
      o1.quantity + o2.quantity
    )
  def empty = Order(0, 0)

}
val order1 = Order(10, 1)
val order2 = Order(2, 2)
val orderList = List(order1, order2)

monoid.combine(order1, order2)

// how do I apply add?