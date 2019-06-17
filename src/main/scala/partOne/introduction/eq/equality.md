# Equality

We can use Eq to define type-safe equality between instances of any given
type.

The interface syntax, defined in `cats.syntax.eq`, provides two methods for
performing equality checks provided there is an instance Eq[A] in scope:

- `===` compares two objects for equality
- `=!=` compares two objects for inequality

Comparing values of different types will give a compiler error.