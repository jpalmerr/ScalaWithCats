/*
cats.data.State allows us to pass additional state around as part of a computation.
In this way we can model mutable state in a purely functional way,
without using mutation.
 */

import cats.data.State
val a = State[Int, String] {
  state =>
    (state, s"The state is $state")
}

/*
In other words, an instance of State is a function that does two things:
- transforms an input state to an output state
- computes a result
 */

// Get the state and the result:
val (state, result) = a.run(10).value
//state: Int = 10
//result: String = The state is 10

// Get the state, ignore the result:
val stateOnly = a.runS(10).value
// state: Int = 10

// Get the result ignore the state
val resultOnly = a.runA(10).value
// resultOnly: String = The state is 10

// ---------------------------------------------------------------------

val step1 = State[Int, String] { num =>
  val ans = num + 1
  (ans, s"Result of step1: $ans")
}
// step1: cats.data.State[Int,String] = cats.data.

val step2 = State[Int, String] { num =>
  val ans = num * 2
  (ans, s"Result of step2: $ans")
}
// step2: cats.data.State[Int,String] = cats.data.

val both = for {
  a <- step1
  b <- step2
} yield (a, b)

val (theState, theResults) = both.run(20).value
//theState: Int = 42
//results: (String, String) = (Result of step1: 21,Result of step2: 42)

// -----------------------------------------------------

/*
The general model for using the State monad is to represent each step of a
computation as an instance and compose the steps using the standard monad
operators. Cats provides several convenience constructors for creating primitive steps:
 */

// get extracts the state as the result
val getDemo = State.get[Int]
getDemo.run(10).value
// (Int, Int) = (10,10)

// set updates the state and returns unit as the result
val setDemo = State.set[Int](30)
setDemo.run(10).value
// (Int, Unit) = (30,())

// pure ignores the state and returns a supplied result
val pureDemo = State.pure[Int, String]("Result")
pureDemo.run(10).value
// (Int, String) = (10,Result)

// inspect extracts the state via a transformation function
val inspectDemo = State.inspect[Int, String](_ + "!")
inspectDemo.run(10).value
// (Int, String) = (10,10!)

// modify updates the state using an update function
val modifyDemo = State.modify[Int](_ + 1)
modifyDemo.run(10).value
// (Int, Unit) = (11,())

// ----------------------------------

import State._
val program: State[Int, (Int, Int, Int)] = for {
  a <- get[Int]
  _ <- set[Int](a + 1)
  b <- get[Int]
  _ <- modify[Int](_ + 1)
  c <- inspect[Int, Int](_ * 1000)
} yield (a, b, c)

val (states, results) = program.run(1).value

// states: Int = 3
//results: (Int, Int, Int) = (1,2,3000)