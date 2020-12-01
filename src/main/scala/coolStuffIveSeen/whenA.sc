/**
 * Returns the given argument (mapped to Unit) if `cond` is `true`, otherwise,
 * unit lifted into F
*/

import cats.Applicative
import cats.implicits._

Applicative[List].whenA(true)(List(1, 2, 3))
// List[Unit] = List((), (), ())

Applicative[List].whenA(false)(List(1, 2, 3))
// List[Unit] = List(())

