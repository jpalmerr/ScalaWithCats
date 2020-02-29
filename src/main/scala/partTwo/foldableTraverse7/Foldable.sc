/*
Foldable abstracts the familiar foldLeft and foldRight opera􏰀tions
=> we can write generic folds that work with a variety of sequence types
 */

// FOLD RECAP

/*
folding:
We supply an accumulator value and a binary func􏰄on to combine it with each item in the sequence
 */
def show[A](list: List[A]): String =
  list.foldLeft("nil")((accum, item) => s"$item then $accum")

show(Nil)
// String = nil
show(List(1, 2, 3))
// String = 3 then 2 then 1 then nil

/*
The foldLeft method works recursively down the sequence.

- Our binary functi􏰀on is called repeatedly for each item,
  the result of each call becoming the accumulator for the next.

- When we reach the end of the sequence, the final accumulator becomes our final result.
 */

// An interesting reflection

List(1, 2, 3).foldLeft(List.empty[Int])((a, i) => i :: a)
// List[Int] = List(3, 2, 1)

List(1, 2, 3).foldRight(List.empty[Int])((i, a) => i :: a)
// List[Int] = List(1, 2, 3)

