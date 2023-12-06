trait MyThings

case class Title(id: String) extends MyThings
case class Break(id: String) extends MyThings

val schedule: List[MyThings] = List(Title("title1"), Break("break1"), Break("2"))

val scheduleWithNoBreaks: List[MyThings]  = List(Title("title1"))

schedule.collect {
  case break: Break => break
}

scheduleWithNoBreaks.collect {
  case break: Break => break
}