import cats.implicits._

val seq: Seq[String] = Seq("hello", "world", "abc", "def")

val x = for {
  s <- seq
} yield s.concat(", ")

seq.mkString(", ")
x.mkString(", ")

def mapToOption(s: String) = s.some

val x: Seq[Option[String]] = seq.map(mapToOption)

val res = x.mkString(", ").replace("Some(", "").replace(")", "")


val aseq: Seq[Option[String]] = Seq("hello".some, "world".some, "abc".some, "def".some, None)

aseq.mkString(",").replace("Some(", "").replace(")", "").replace(",None", "")