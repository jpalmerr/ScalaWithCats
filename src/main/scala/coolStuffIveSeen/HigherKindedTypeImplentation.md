Something I've come across at work is defining the type such as Monoid.

A cool explanation/example from Cats as to why I've seen this.

-----------


Explicits over Implicits

Remember that Scala will only use an instance of Foldable
if the method isn’t explicitly available on the receiver. 

For example, the following code will use the version
of foldLeft defined on List:


```scala worksheet
List(1, 2, 3).foldLeft(0)(_ + _)
// Int = 6
```

whereas the following generic code will use Foldable:

```
import scala.language.higherKinds

def sum[F[_]: Foldable](values: F[Int]): Int =
  values.foldLeft(0)(_ + _)

// sum: [F[_]](values: F[Int])(implicit evidence$1: cats. Foldable[F])Int
```
