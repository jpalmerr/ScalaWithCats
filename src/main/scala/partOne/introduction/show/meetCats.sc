//  In this section we will look at how
//  type classes are implemented in Cats.

import cats.Show

import cats.instances.int._ // for Show
import cats.instances.string._ // for Show

val showInt: Show[Int] = Show.apply[Int]
val showString: Show[String] = Show.apply[String]

val intAsString: String =
  showInt.show(123) // String = 123

val stringAsString: String =
  showString.show("abc") // String = abc

println("-------------------------------------------")

// We can make Show easier to use by importing
// the interface syntax from cats.syntax.show

import cats.syntax.show._ // for show

val shownInt = 123.show
// shownInt: String = 123

val shownString = "abc".show
// shownString: String = abc

/*
a lot of files just start with

import cats._
import cats.implicits._
 */