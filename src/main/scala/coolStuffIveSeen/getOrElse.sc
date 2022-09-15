import cats.effect.IO

def makeCall(id: String): IO[Option[Int]]

case class Response(id: Option[String])

val response: IO[Response] = ???

val x: IO[Option[Int]] = response.flatMap { res =>

}