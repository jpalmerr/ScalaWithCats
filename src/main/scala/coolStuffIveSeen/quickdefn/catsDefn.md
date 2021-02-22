## Functors

A functor captures the notion of something you can map over, changing its
“contents” (or output) but not the structure itself.

```scala
List(1, 2, 3).map(_ + 1) // List(2, 3, 4)
Option(1).map(_ + 1) // Some(2)
Future(1).map(_ + 1) // ...eventually Future(2)
```

## Applicatives

An applicative functor, also known as applicative,
is a functor that can transform multiple structures, not just one.

```scala
def mapN
```

```scala
val third: F[B] = first *> second 1
```
```
The *> method composes two effects, first and second, via mapN.
If both effects succeed, we ignore the first effect’s value,
only returning the second effect’s value.
```

## Monads

```
A monad is a mechanism for sequencing computations:
this computation happens after that computation.

Roughly speaking,
a monad provides a flatMap method for a context F[A]:
```

```scala
def flatMap[B](f: A => F[B]): F[B]
```
