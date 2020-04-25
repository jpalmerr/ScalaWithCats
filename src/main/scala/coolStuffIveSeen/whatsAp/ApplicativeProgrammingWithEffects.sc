// ZIO

import zio._
import zio.console._
import zio.clock._
import zio.duration._
import cats.Applicative
import cats.data.Reader
import cats.implicits._

/*
When you create a future it runs immediately and you cannot run it again.
You can create a ZIO and run it when you decide to and as many times as you want.
 */

// monadic sequence

def delayedPrintNumber(s: Int): ZIO[Console with Clock,String,Int] = {
  putStrLn(s"Preparing to say number in $s seconds") *>
    putStrLn(s"$s").delay(s.seconds) *>
    ZIO.succeed(s)
}
val ios1 = List(6,5,2,1,3,8,4,7).map(delayedPrintNumber)

// monadicSequence function cannot possibly run all the effects at once by virtue of it being monadic in the first place

// an applicative sequence allows us to parellelise our effects

// Transpose

/*
Original matrix
 1  2  3  4  5
 6  7  8  9 10
11 12 13 14 15

Transposed matrix
 1  6 11
 2  7 12
 3  8 13
 4  9 14
 5 10 15
 */

// Evaluating

sealed trait Exp
case class Val(value: Int) extends Exp
case class Add(left: Exp, right: Exp) extends Exp
case class Var(key: String) extends Exp

case class Env[K](kv: Map[K,Int])

def fetch(key: String)(env: Env[String]) : Int =
  env.kv.getOrElse(key, 0)

def eval(exp: Exp, env: Env[String]) : Int = {
  exp match {
    case Val(value) => value
    case Var(key) => fetch(key)(env)
    case Add(left, right) =>
      eval(left, env) + eval(right, env)
  }
}

def fetchR(key: String) =
  Reader[Map[String,Int], Int](env => env.getOrElse(key, 0))
def pureR(value: Int) =
  Reader[Map[String,Int], Int](env => value)

def evalR(exp: Exp): Reader[Map[String,Int], Int] = {
  exp match {
    case Val(value) => pureR(value)
    case Var(key) => fetchR(key)
    case Add(left, right) =>
      val f = Reader((env:Map[String,Int]) =>
        (a:Int) => (b:Int) => a + b)
      val leftEval = evalR(left).ap(f)
      evalR(right).ap(leftEval)
  }
}

val env1 = Env(Map("x" -> 3, "y" -> 10))
val exp1 = Add(Val(10), Add(Var("x"), Var("y")))

println(s"Eval : ${eval(exp1, env1)}")