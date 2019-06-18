// COMPARING INTS

import cats.instances.int._ // instance for Int
import cats.syntax.eq._ // for === and =!=


123 === 123
// Boolean = true
123 =!= 234
// Boolean = true

123 == 123 // scala library

// COMPARING OPTIONS

// compiler error comparing none and some so...

import cats.instances.option._ // so we have quick instances...
import cats.syntax.option._

1.some === none[Int] // false
1.some =!= none[Int] // true
// can still compare same types of course
1.some === 1.some // true

