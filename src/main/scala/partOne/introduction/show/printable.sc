/* Scala provides a toString method to let us convert any value to a String.
* However, this method comes with a few disadvantages.
* Let’s define a Printable type class to work around these problems.
 */

trait Printable[A] {
  def format(value: A): String
}

// Define some default instances of Printable
// package them in PrintableInstances

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    def format(input: String) = input
  }
  implicit val intPrintable = new Printable[Int] {
    def format(input: Int) = input.toString
  }
}

// define interface object

object Printable {
  def format[A](input: A)(implicit p: Printable[A]): String =
    p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit =
    println(format(input))
}


// using the library ...

final case class Cat(name: String, age: Int, color: String)

import PrintableInstances._

implicit val catPrintable = new Printable[Cat] {
  def format(cat: Cat) = {
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }
}

val cat = Cat("Garfield", 12, "Ginger")
Printable.print(cat)
// Garfield is a 12 year-old Ginger cat.


// NICER SYNTAX

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)
    def print(implicit p: Printable[A]): Unit =
      println(format(p))
  }
}

import PrintableSyntax._
Cat("Garfield", 38, "ginger and black").print

// Garfield is a 38 year-old ginger and black cat.