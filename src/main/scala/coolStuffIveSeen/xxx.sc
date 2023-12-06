def addRecursively(list: List[Int]): Int = {
  def loop(remaining: List[Int], accum: Int = 0): Int = {
    remaining match {
      case ::(head, tail) => {
        println(s"head: $head")
        println(s"tail: $tail")
        println(s"accumulator: $accum")
        println("-----")
        loop(tail, accum + head)
      }
      case Nil =>
        println(s"remaining list is now empty: $remaining")
        println("so return what we've accumulated")
        accum
    }
  }
  loop(list)
}

val data = List(1, 1, 2, 3, 5, 8)

//data.sum
addRecursively(data)

