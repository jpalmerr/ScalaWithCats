import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.instances.invariant._ // for Semigroupal
import cats.instances.list._ // for Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.apply._ // for imapN

case class Cat(
                name: String,
                yearOfBirth: Int,
                favouriteFoods: List[String]
              )

val tupleTocat: (String, Int, List[String]) => Cat =
  Cat.apply _

val catToTuple: Cat => (String, Int, List[String]) =
  cat => (cat.name, cat.yearOfBirth, cat.favouriteFoods)

implicit val catMonoid: Monoid[Cat] = (
  Monoid[String],
  Monoid[Int],
  Monoid[List[String]]
).imapN(tupleTocat)(catToTuple)

/*
Our monoid allows us to create "empty" cats and add cats together

  ...note: combine and empty defn
 */

import cats.syntax.semigroup._ // |+|

val garfield = Cat("Garfield", 1978, List("Lasagne"))
val heathcliff = Cat("Heathcliff", 1988, List("Junk Food"))

garfield |+| heathcliff
// Cat = Cat(GarfieldHeathcliff,3966,List(Lasagne, Junk Food))
