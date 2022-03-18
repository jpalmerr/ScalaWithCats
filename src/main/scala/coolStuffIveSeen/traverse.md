
# Traverse

Traverse is the answer to everything.

- Quite often there is a need to apply a function to every element in a list `List[A]`,
- but the function returns `F[B]`,
- so the result is `List[F[B]]` which isn’t very useful.

- What you really want is `F[List[B]]`
- traverse fixes that.

```scala
def doX(a: A): F[B] = ???

val l = List(a1, a2)

l.traverse(a => doX(a)) // F[List[B]]
```

It doesn’t have to be a list, it can be any higher-kinded type.

the full signature for traverse is:

```scala
trait Traverse[F[_]] {
  def traverse[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]
}
```

Traverse is `map` followed by `sequence`.
Thats it.