/*
wrote some code at work that involved writing two apply methods
little example
 */


case class Thing(number: Int, maybeString: Option[String]) {
  def this(number: Int) = this(number, None) // auxillary
}

object Thing {

  def apply(number: Int, maybeString: Option[String]): Thing =
    new Thing(number, maybeString)

  def apply(number: Int): Thing =
    new Thing(number)
}

val thing1 = Thing(10, Some("two params!"))
val thing2 = Thing(11)

thing1.maybeString // Option[String] = Some(two params!)
thing1.number // Int = 10

thing2.maybeString // Option[String] = None
thing2.number // Int = 11