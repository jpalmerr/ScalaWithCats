import cats.data.Reader

case class Cat(name: String, favouriteFood: String)

val catName: Reader[Cat, String] =
  Reader(cat => cat.name)

catName.run(Cat("Garfield", "lasagne"))
// cats.Id[String] = Garfield

val greetKitty: Reader[Cat, String] =
  catName.map(name => s"Hello $name")

greetKitty.run(Cat("Heathcliff", "junkfood"))

/*
 notice pattern:
 - set of readers
 - combine
 - run
 */

// The flatMap method is more interesting.
// It allows us to combine readers that depend on the same input type.

val feedKitty: Reader[Cat, String] =
  Reader(cat => s"Have a nice bowl of ${cat.favouriteFood}")

val greetAndFeed: Reader[Cat, String] =
  for {
    greet <- greetKitty
    feed <- feedKitty
  } yield s"$greet. $feed."

val garfield = Cat("Garfield", "lasagne")

greetAndFeed.run(garfield)
// Hello Garfield. Have a nice bowl of lasagne.

greetAndFeed.run(Cat("Heathcliff", "junkfood"))
// Hello Heathcliff. Have a nice bowl of junkfood.