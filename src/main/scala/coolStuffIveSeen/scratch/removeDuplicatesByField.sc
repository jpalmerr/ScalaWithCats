case class Item(name: String, number: Int)

val item1 = Item("James", 10)
val item2 = Item("Adam", 10)
val item3 = Item("James", 12)

val items = List(item1, item2, item3)

val x: List[Item] = items.groupBy(_.name).values.map(_.head).toList
items.groupBy(_.number).values.map(_.head).toList


items.filter(_.number == 10)
items.filterNot(_.number == 10)