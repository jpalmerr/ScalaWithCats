import cats.implicits.catsSyntaxOptionId

val list1: List[String] = List("hello", "world", "I", "am", "James")
val list2: List[String] = List("hello", "James", "random")

list1.filter { word =>
  list2.contains(word)
}


case class Response(variantId: String, subtitlesLocation: Option[String], featureSet: List[String])

val variantsToFilter: List[String] =
  List("VAR1", "VAR02", "VAR2", "VAR3")

val givenFeatureSet: List[String] =
  List("feature1, feature2")

val var1Res = Response("VAR4", "subtitles".some, List("feature1", "feature2"))
val var2Res = Response("VAR5", "subtitles".some, List("feature1", "feature4"))
val var3Res = Response("VAR1", "subtitles".some, List("feature3", "feature4"))

val variantResponse = List(var1Res, var2Res, var3Res)

val filteredBB: List[Response] = variantResponse
  .filterNot(p => variantsToFilter.contains(p.variantId))

def filterFeatureSet()
filteredBB match {
  case ::(head, next) => ???
  case Nil => ???
}




