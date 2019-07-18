# Writer Monad

`cats.data.Writer` is a monad hat lets us carry a log along with a computation,

We can use it to record messages, errors, or addition.

A `Writer[W, A]` carries two values: a log of type W and a result of type A.

```scala
import cats.data.Writer
import cats.instances.vector._ // for Monoid
Writer(Vector(
	"It was the best of times",
	"it was the worst of times"
), 1859)
```

