# Reader Monad

`cats.data.Reader` is a monad that allows us to:

-  sequence operations that depend on some input.

Instances of Reader

- wrap up functions of one argument
- provides us with useful methods for composing them

One common use for Readers is **dependency injection**.

--------

*But what advantage do Readers give us over raw functions?*

The power of Readers comes from their `map` and `flatMap` methods.

We typically:

- create a set of Readers
  - that accept the same type of configuration
- combine with `map` and `flatMap`
- then call run to inject the config at the end.

----

*When to use Readers?*

Readers provide a tool for doing dependency injection. 

- Write steps of program as instances of Reader
- chain them together using `map` and `flatMap`
- build a function that accepts the dependency as input

Readers are most useful in sutations where:

- we are constructing a batch program that can easily be represented by
  a function
- we need to defer injection of a 
  - known parameter
  - set of parameters
- we want to be able to test parts of the program in isolation

By representing the steps of our program as Readers we can *test them as easily as pure functions*, plus we gain access to the map and flatMap combinators.

