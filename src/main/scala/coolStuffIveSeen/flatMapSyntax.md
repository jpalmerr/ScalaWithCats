 ```scala
 /**
   * Alias for [[flatMap]].
   */

  def >>=[B](f: A => F[B])(implicit F: FlatMap[F]): F[B] = F.flatMap(fa)(f)

  /**
   * Alias for `fa.flatMap(_ => fb)`.
   *
   * Unlike `*>`, `fb` is defined as a by-name parameter, allowing this
   * method to be used in cases where computing `fb` is not stack safe
   * unless suspended in a `flatMap`.
   */

  def >>[B](fb: => F[B])(implicit F: FlatMap[F]): F[B] = F.flatMap(fa)(_ => fb)
```
