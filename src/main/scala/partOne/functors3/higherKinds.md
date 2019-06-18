# An aside

## Higher Kinds

Kinds are like types for types.

They describe the number of “holes” in a type.

For example, List is a type constructor with one hole. We fill that hole by
specifying a parameter to produce a regular type like List[Int] or List[A].

--------
Higher kinded types are considered an advanced language feature in
Scala. Whenever we declare a type constructor with A[_] syntax, we
need to “enable” the higher kinded type language feature to suppress
warnings from the compiler. 

We can either do this with a “language
import”:

```
import scala.language.higherKinds
```

or  by adding the following to scalacOptions in build.sbt:

```
scalacOptions += "-language:higherKinds"
```
------------