// rather than in printable lets apply cats ...

import cats.Show
import cats.instances.int._ // for Show
import cats.instances.string._ // for Show
import cats.syntax.show._ // for show

final case class Cat(name: String, age: Int, color: String)

// just use the cats library in companion object instead:
implicit val catShow = Show.show[Cat] { cat =>
  val name = cat.name.show
  val age = cat.age.show
  val color = cat.color.show
  s"$name is a $age year-old $color cat."
}

println(Cat("Garfield", 38, "ginger and black").show)

// Garfield is a 38 year-old ginger and black cat.