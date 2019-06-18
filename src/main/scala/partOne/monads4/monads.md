# Monads

Informally, a monad is anything with a constructor and a flatMap method.

A monad is a mechanism for sequencing computations.

[Monad picture](https://github.com/jpalmerr/ScalaWithCats/blob/master/public/MonadPicture.png)

A monad’s flatMap method allows us to specify what happens next, 
taking into account an intermediate complication.

## Examples

##### Option

Option allows us to sequence computations that may or may not return values.
Here are some examples:
 
 ```$xslt
def parseInt(str: String): Option[Int] =
scala.util.Try(str.toInt).toOption

def divide(a: Int, b: Int): Option[Int] =
if(b == 0) None else Some(a / b)
```

Each of these methods may “fail” by returning None. The flatMap method
allows us to ignore this when we sequence operations:

`Option[A] flatmap A => Option[B] -> Option[B]`

At each step, flatMap chooses whether to call our function, and our funcঞon
generates the next computation in the sequence.

The result of the computation is an Option, allowing us to call flatMap again
and so the sequence continues.

##### Lists

When we first encounter flatMap as budding Scala developers,
we tend to think of it as a pattern for iterating over Lists.

However, there is another mental model we can apply that highlights the
monadic behaviour of List.
**If we think of Lists as sets of intermediate results, 
flatMap becomes a construct that calculates permutations and combinations.**

## Definition of Monads

- pure, of type [A] => F[A]
- flatMap, of type (F[A], A => F[B]) => F[B]

*pure abstracts over constructors, providing a way to create a new monadic
 context from a plain value.*
 
 Simplified monad type class:
 
 ```
 import scala.language.higherKinds
 
 trait Monad[F[_]] {
    def pure[A](value: A): F[A]
    
    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
 }
 ```
 
 ____
 Monad Laws:
 
 `pure` and `flatMap` must obey a set of laws that allow us to sequence
 operations freely without unintended glitches and side-effects:
 
 *Left identity:*
 
 `pure(a).flatMap(func) == func(a)`
 
 *Right identity:*
 
 `m.flatMap(pure) == m`
 
 Associativity: 
 
 - flatMapping over two functions f and g
 
 same as
 
- flatMapping over f and then flatMapping over g
 
 `m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))`