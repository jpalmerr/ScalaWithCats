### Lift in Scala Docs

Lift a function f to operate on Functors

```
def lift[A, B](f: A => B): F[A] => F[B] = map(_)(f)
```