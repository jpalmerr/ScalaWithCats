# Semigroupal and Applicative

We have seen how **functors** and **monads** let us sequence operations using `map` and `flatMap`.
However, there are certain types of program flow they cannot represent.

Example: Form validation

When we validate a form we want to return *all* the errors to the user
    - not to stop on the first error we encounter.
If we model this with a monad (like `Either`), we **fail fast** and lose errors.

Another example is `Futures`. If we have several long running independent tasks,
it makes sense to run them concurrently. 
    - Monadic comprehension only allows us to run them in sequence.

**We need a weaker construct** —one that doesn’t guarantee sequencing

- `Semigroupal` encompasses the notion of composing pairs of contexts.
    - Cats provides `cats.syntax.apply` that makes use of `Semigroupal` and `Functor`
      to allow users to sequences functions with multiple arguments.
      
- `Applicative` extends `Semigroupal` and `Functor`.
    - Provides a way of applying functions to parameters within a context.     

