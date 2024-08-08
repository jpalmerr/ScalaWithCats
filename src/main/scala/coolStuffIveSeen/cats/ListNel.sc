import cats.data.NonEmptyList
import scala.collection.immutable.SortedMap
import cats.implicits._

val list = List(12, -2, 3, -5)
val expectedResult = SortedMap(false -> NonEmptyList.of(-2, -5), true -> NonEmptyList.of(12, 3))
list.groupByNel(_ >= 0) === expectedResult

list.groupByNel(_ > 10) // Map(false -> NonEmptyList(-2, 3, -5), true -> NonEmptyList(12))

list.groupByNel(_ >= 0).mapValues(_.head) // Map(false -> -2, true -> 12)

list.groupByNel(_ >= 0).mapValues(_.map(_ + 1)) // Map(false -> NonEmptyList(-1, -4), true -> NonEmptyList(13, 4))