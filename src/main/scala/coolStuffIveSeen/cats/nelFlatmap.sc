import cats.data.NonEmptyList

case class MyType(name: String, age: Option[Int])

// Construct a NonEmptyList of Option[Int]
val nonEmptyList: NonEmptyList[MyType] = NonEmptyList.of(MyType("1", Some(1)), MyType("2", Some(2)), MyType("3", None))

nonEmptyList.flatMap(_.age)

// Use flatMap to flatten the Option inside the NonEmptyList


