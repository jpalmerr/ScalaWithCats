val res = List(1, 2, 3, 4, 5)

//res(2)
//res(7)

res.lift(7)
res.lift(2)

res.lift(7).toList
res.lift(2).toList


res ++ Some(6)
res ++ None
//
//
//
//def sliceBy(list: List[Int], index: Int): List[Int] = {
//  list.slice(index -1, index + 1)
//}
//
//sliceBy(List(1, 2), 1)
//sliceBy(List(1, 2, 3), 1)
//
//
//def takeBy(list: List[Int], index: Int): List[Int] = {
//  list.takeRight(index - 1)
//}
//
//takeBy(List(1), 0)
