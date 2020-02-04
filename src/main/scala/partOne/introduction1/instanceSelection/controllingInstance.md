# Controlling Instance Selection

When working with type classes we must consider two issues that control
instance selection:

- What is the relationship between an instance defined on a type and its
  subtypes?
  
- How do we choose between type class instances when there are many
  available?
  
  
## Variance

When we define type classes **we can add variance annotations** to the type
parameter to affect the variance of the type class and the compiler’s ability to
select instances during implicit resolution.

-----------
Remember that variance is all about the ability
to substitute one value for another.
-----------

### Covariance

----------------------------------
Covariance means that the type F[B] is a subtype of the type F[A],
if B is a subtype of A.
----------------------------------

 This is useful for modelling many types, including collections like
*List* and *Option*:

```$xslt
trait List[+A]
trait Option[+A]
```

The covariance of Scala collections allows us to substitute collections of one
type for another in our code.

E.g. 
We can use `List[Circle]` anywhere we expect `List[Shape]`, 

since **`Circle` is a subtype of `Shape`**.

### Contravariance

--------
Contravriance means that the type F[B] is a subtype of F[A],
if A is a subtype of B.
--------

This is useful for modelling types that represent processes.

```$xslt
trait F[-A]
```

### Invariance

```$xslt
trait F[A]
```

This means the types `F[A]` and `F[B]` are never subtypes of one another, no
matter what the relationship between A and B. This is the default semantics
for Scala type constructors.

Two issues tend to arise.

1. Will an instance defined on a supertype be selected if one is available?
   
   E.g. can we define an instance for A and have it work for values
   of type B and C?
   
2. Will an instance for a subtype be selected in preference to that of a
   supertype?
    
    For instance, if we define an instance for A and B, and we
   have a value of type B, will the instance for B be selected in preference
   to A?
      
It turns out we can’t have both at once. The three choices give us behaviour
as follows:

| **Type class variance** | **Invariant** | **Covariant** |**Contravariant** |
| :---:        |     :---:      |      :---:    |     :---:      |
| Supertype instance used? | No | No | Yes |
| More specific type preferred? | No | Yes | No |

It’s clear there is no perfect system. **Cats generally prefers to use invariant type
classes**. This allows us to specify more specific instances for subtypes if we
want.

  