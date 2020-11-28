sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json
// The "serialize to JSON" behaviour is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person): Json =
      JsObject(Map(
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }
}

/*
A type class interface is any func􏰀tionality we expose to users.
Interfaces are generic methods that accept instances of the type class as implicit parameters.
There are two common ways of specifying an interface: Interface Objects and Interface Syntax.
 */

// Interface objects

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

import JsonWriterInstances._

Json.toJson(Person("James", "james@gmail.com")) // personWriter passed implicitly

/*
We can alterna􏰀tively use extension methods to extend existi􏰀ng types with interface methods.
Cats refers to this as “syntax” for the type class
 */

// Interface syntax

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]) =
      w.write(value)
  }
}

import JsonSyntax._

Person("James", "James@gmail.com").toJson

/*
Recursive Implicit Resoluti􏰀on

The power of type classes and implicits lies in the compiler’s ability to combine implicit definition􏰀ons when searching for candidate instances.

We can actually define instances in two ways:
1. by defining concrete instances as implicit vals of the required type;
2. by defining implicit methods to construct instances from other type class instances.
 */

implicit def optionWriter[A]
(implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
  new JsonWriter[Option[A]] {
    def write(option: Option[A]): Json =
      option match {
        case Some(aValue) => writer.write(aValue)
        case None         => JsNull
      } }


Json.toJson(Option("A string")) // optionWriter(stringWriter)