# Monoids and Semigroups

In this section we explore our first type classes, monoid and semigroup. These
allow us to add or combine values. 

## Integer Addition

Addition is **closed**.

i.e. Addition of two `Int`s will always produce another `Int`s.

**Identity** element is 0.

```$xslt
2 + 0 = 0
0 + 2 = 0
``` 
=> identity = 0

**Associativity**:

The order we add elements doesn't matter.

```$xslt
(1 + 2) + 3 = 6
1 + (2 + 3) = 6
``` 

## Integer Multiplication

The same properties apply:

**Identity**: 1

```$xslt
1 * 5 = 5
5 * 1 = 5
```

**Associative**: Yes

```$xslt
(1 * 2) * 3 = 6
1 * (2 * 3) = 6
```

## String Concatenation

We can also do the same with strings, using the *empty string*
as the identity.