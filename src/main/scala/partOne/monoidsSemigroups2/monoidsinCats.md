# Mondoids in Cats

Now we've seen what monoids are, lets look at their implementation in Cats.
We'll look at the three main aspects of the implementation:

- the type class
- the instances
- the interface

## The Monoid Type Class

```$xslt
import cats.Monoid
import cats.Semigroup
```

## Monoid Instances

Monoids follow the standard Cats pattern for the user interface:

the companion object has an apply method that returns he type class instance
for a particular type.

##### String

```$xslt
import cats.Monoid
import cats.instances.string._  // for Monoid

Monoid[String].combine("Hi ", "there")
// res0: String = Hi there

Monoid[String].empty
// res1: String = ""
```  

##### Ints

```$xslt
import cats.Monoid
import cats.instances.int._ // for Monoid

Monoid[Int].combine(32, 10)
// res5: Int = 42
```

##### Option

```
import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.instances.option._ // for Monoid

val a = Option(22)
// a: Option[Int] = Some(22)

val b = Option(20)
// b: Option[Int] = Some(20)

Monoid[Option[Int]].combine(a, b)
// res6: Option[Int] = Some(42)
```