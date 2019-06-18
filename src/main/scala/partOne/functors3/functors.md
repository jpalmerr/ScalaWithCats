# Functors

In this chapter we will investigate functors, an abstraction that allows us to
represent sequences of operations within a context such as a List, an Option,
or any one of a thousand other possibilities.

Functors on their own aren't so useful...but special cases,
such as **monads** and **applicative functors**are some of the most
commonly used abstractions in cats.

## Examples of functors

Informally, a functor is anything with a map method.
- Option
- Either
- List ... etc

Think about how, when you map over an Option for example,
the `Some` or `None` context remains unchanged.

##### Futures

Future is a functor that sequences asynchronous computations by queueing
them and applying them as their predecessors complete. 

When we work with a Future we have no guarantees about its internal state.
The wrapped computation may be ongoing, complete, or rejected.

We don’t know when our functions will be called, but we do know what
order they will be called in. In this way, Future provides the same sequencing
behaviour seen in List, Option, and Either.

##### Functions (!!!)

Yes, functions are an example of a functor too.

[functor map](/public/functorMap.png)

# Definition of a Functor

Every example we’ve looked at so far is a functor: a class that encapsulates
sequencing computations.

Formally, a functor is:

type F[A] with an operation map (A => B) => F[B].

![functor def](/Users/student/Documents/Scala/CatsScala/public/FunctorPictures.png)

