```scala
final case class OptionT[F[_], A](value: F[Option[A]])
```

hence the effect of `.value` !!!