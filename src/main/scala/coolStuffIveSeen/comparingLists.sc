/*
replicating scenario and solving for work problem
 */

case class Wrapper1(id: String, otherField: Int)
case class Wrapper2(id: String, otherField: Int)

val list1: List[Wrapper1] = List(Wrapper1("abc", 1), Wrapper1("def", 2), Wrapper1("ghi", 3), Wrapper1("mno", 4))
val list2: List[Wrapper2] = List(Wrapper2("xyz", 1), Wrapper2("abc", 2), Wrapper2("123", 3), Wrapper2("mno", 4))

val ids1: List[String] = list1.map(_.id)
val ids2: List[String] = list2.map(_.id)

def methodWrapper2(goodIds: List[Wrapper2]): Unit =
  goodIds.foreach { wrapper2 =>
    println(s"id: ${wrapper2.id}")
  }

ids2.exists(s => ids1.exists(_.contains(s)))

val intersect: List[String] = ids1.intersect(ids2)


val result: List[Wrapper2] = list2.filter { wrapper2 =>
  intersect.contains(wrapper2.id)
}

methodWrapper2(result)

def filter(wrapper1s: List[Wrapper1], wrapper2s: List[Wrapper2]): List[Wrapper2] = {
  val id1s: List[String] = wrapper1s.map(_.id)
  val id2s: List[String] = wrapper2s.map(_.id)
  val intersect: List[String] = id1s.intersect(id2s)

  wrapper2s.filter { wrapper2 =>
    intersect.contains(wrapper2.id)
  }
}

def program(listA: List[Wrapper1], listB: List[Wrapper2]): Unit = {
  methodWrapper2(filter(listA, listB))
}

program(list1, list2)