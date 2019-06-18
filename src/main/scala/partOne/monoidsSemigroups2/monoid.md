# Monoid

An example of a Monoid is the addition scenario we have just seen.

Formally,

A monoid for type A is:

- An operation combine with type (A, A) => A
- An element empty of type A

Informally, 

- Has an identity
- Is associative 

A simplified version of the definition from the cats library is:

```$xslt
trait Monoid[A] {
    def combine(x: A, y: A): A
    def empty: A
}
```

Integer subtraction, for example, is not a monoid as it is not associative.

```$xslt
1 - (2 - 3) = 2 != -4 = (1 - 2) - 3
```

In practice we only need to think about laws when we are writing our own
Monoid instances. Most of the time we can rely on he instances provided by Cats
and assume the library authors know what they're doing.

# Semigroup

----
A semigroup is just the combine 
part of a monoid.
----

While many semigroups are also monoids, there are some data types for which
we cannot define an empty element.

E.g. we have just seen that sequence concatenation and integer addition are monoids.
     However, if we restrict ourselves to non-empty sequences and positive integers,
     we are no longer able to define a sensible empty element.
     
 ```$xslt
trait Semigroup[A] {
    def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
    def empty: A
}
```