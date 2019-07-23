# Summary

In this chapter we’ve seen monads up-close.

We saw that **flatMap** can be viewed as an operator for:

**sequencing computations, dictating the order in which operations must happen**.

From this viewpoint:

- `Option` represents a computation that can fail
  - without an error message
- `Either` represents a computation that can fail
  - with a message
- `List` represents multiple possible results
- Future represents a computation that may produce a value at some point in the future

----

Also seen some of the custom types and data structures that Cats provides, including:

- `Id`
- `Reader`
- `Writer`
- `State`

These cover a wide range of use cases.