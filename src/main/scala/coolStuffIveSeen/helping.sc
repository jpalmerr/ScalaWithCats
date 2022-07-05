def listToMap(input: List[List[String]], acc: Map[String, String] = Map.empty): Map[String, String] =
  input match {
    case ::(head, next) => {
      if
      (head.length < 2) listToMap(next, acc)
      else
        listToMap(next, acc + (head.head -> head.last))
    }
    case Nil => acc
  }

def convertListToMap(list: List[String], acc: Map[String, String]): Map[String, String]

def listToMap2(input: List[List[String]], acc: Map[String, String] = Map.empty): Map[String, String] = {
  input.flatMap { innerList =>
    if (innerList.length <= 2) {
      Map(innerList.) // conversion
    } else {
      acc
    }
  }
}.toMap


listToMap(List(List("elem1", "elem2")))

