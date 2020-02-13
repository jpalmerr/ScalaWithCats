// LIST

/*
Combining Lists with Semigroupal produces some potentially unexpected
results. We might expect code like the following to zip the lists, but we actually
get the cartesian product of their elements:
 */

import cats.Semigroupal
import cats.instances.list._ // for Semigroupal
Semigroupal[List].product(List(1, 2), List(3, 4))
// List[(Int, Int)] = List((1,3), (1,4), (2,3), (2,4))

// EITHER

/*
We opened this chapter with a discussion of fail-fast versus accumulating error-handling.
We might expect product applied to Either to accumulate errors instead of fail fast.

However, we find that product implements the same fail-fast behaviour as flatMap
 */

/*
The reason for the surprising results for List and Either is that they are both monads.
 */
