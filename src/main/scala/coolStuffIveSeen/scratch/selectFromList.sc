val myList: List[String] =
  List(
    "short synopsis",
    "too long synopsis",
    "longest synopsis",
    "longer synopsis"
  )

myList.map(_.length)


myList.filter(_.length < 17).sortBy(_.length).reverse.headOption

val myList2 =
  List(
  "12345 678910",
  "123 456",
  "0123456789", // <- target
  "123"
  )



myList.filter(_.length <= 10).sortBy(_.length).reverse.headOption

def findAllLargestStrings(strings: List[String]): Option[List[String]] = {
  val filterListOfStrings = strings.filter(_.length <= 10)

  if (filterListOfStrings.isEmpty) {
    None // empty string
  } else {
    val largestStringInList = filterListOfStrings.map(_.length).max
    Some(filterListOfStrings.filter(_.length == largestStringInList))
  }
}

val inputList = List(
  "12345678910",
  "123 456",
  "0123456789", // <- target
  "123",
  "3333872243",
  "4028423",
  "3466",
  "99832",
  "230 834 2384"
)

val inputList2 = List(
  // empty list
)

val results = findAllLargestStrings(inputList)