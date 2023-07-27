import cats.implicits._

val varsToAvoid: List[String] = List("VAR040", "VAR041", "VAR042")

case class Response(number: String, link: Option[String])

val responseToAvoid = Response("VAR040", "someLink".some)
val goodRes1 = Response("VAR002", "someLink1".some)
val goodRes2 = Response("VAR003", "someLink2".some)
val goodRes3 = Response("VAR003", none)

val exampleListOne: List[Response] = List(responseToAvoid, goodRes1, goodRes2, goodRes3)

varsToAvoid.contains("VAR004")
varsToAvoid.contains("VAR041")

exampleListOne.filter(p => varsToAvoid.contains(p.number))
exampleListOne.filterNot(p => varsToAvoid.contains(p.number))

exampleListOne.filterNot(p => varsToAvoid.contains(p.number)).exists(_.link.isDefined)


val xxx = List(1, 2, 3, 4)
xxx.filter(_ == 1)
xxx.filterNot(_ == 1)
