// implement an instance of eq

import cats.implicits._ // for ===

import cats.Eq
import cats.instances.int._
import cats.instances.string._

final case class Cat(name: String, age: Int, color: String)

implicit val catEqual: Eq[Cat] =
  Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name === cat2.name) &&
      (cat1.age === cat2.age) &&
      (cat1.color === cat2.color)
  }

val cat1 = Cat("Garfield",   38, "orange and black")
val cat2 = Cat("Heathcliff", 33, "orange and black")
val optionCat1 = Option(cat1)
val optionCat2 = Option.empty[Cat]

cat1 === cat2 // uses catEqual implicit
// false

optionCat1 === Some(cat1)
// true

cat2 === cat2