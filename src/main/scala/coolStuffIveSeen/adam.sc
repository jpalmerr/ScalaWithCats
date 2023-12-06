val myList =
  List(
    "12345 678910",
    "123 456",
    "0123456789", // <- target
    "123"
  )

val myEmptyList: List[String] = List.empty

/*
  Take the longest string, that is under 10 digits
  (ie, if you have one entry with 11 letters, one with 9, one with 7, take out the one with 9)

  Return None for the empty list

  clue: sort the values in your list, then use headOption to take the first entry from your list or None if its empty
 */
def takeLongestString(list: List[String]): Option[String] = {
  ???
}

takeLongestString(myList) // Some("0123456789")
takeLongestString(myEmptyList) // None
