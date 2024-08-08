# EitherT.merge

Often we need to take an Either[A, B] and do something with both sides to produce a consistent type,

e.g. C,

so you end up with Either[C, C].

`merge` will return the C from either (actually the least upper bound)

example: http responses

```scala
Either[Error, Result]
 // map to ->

Either[Response, Response]

val xxx: Either[Response, Response]

xxx.merge // Either[Response]
```

`EitherT[F, C, C].merge` gives you `F[C]`

For example if I have a method returning
```
EitherT[IO[String, String]]
```

calling `.merge` will give me

```
IO[String]
```


