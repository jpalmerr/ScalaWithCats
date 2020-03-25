# flatTap

Something I see often used at work.

From cats documentation 

```
  /**
   * Apply a monadic function and discard the result while keeping the effect.
   *
   * {{{
   * scala> import cats._, implicits._
   * scala> Option(1).flatTap(_ => None)
   * res0: Option[Int] = None
   * scala> Option(1).flatTap(_ => Some("123"))
   * res1: Option[Int] = Some(1)
   * scala> def nCats(n: Int) = List.fill(n)("cat")
   * nCats: (n: Int)List[String]
   * scala> List[Int](0).flatTap(nCats)
   * res2: List[Int] = List()
   * scala> List[Int](4).flatTap(nCats)
   * res3: List[Int] = List(4, 4, 4, 4)
   * }}}
   */

  def flatTap[A, B](fa: F[A])(f: A => F[B]): F[A] =
    flatMap(fa)(a => as(f(a), a))
```

Useful for when you want the left hand case - obviously most cases you expect to want the right case.