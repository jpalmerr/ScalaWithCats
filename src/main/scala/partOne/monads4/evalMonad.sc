import cats.Eval

val now = Eval.now(math.random + 1000)
// now: cats.Eval[Double] = Now(1000.2990430362615)
val later = Eval.later(math.random + 2000)
// later: cats.Eval[Double] = cats.Later@32220dad
val always = Eval.always(math.random + 3000)
// always: cats.Eval[Double] = cats.Always@7553edc9

// access the values
now.value
// 1000.2990430362615
later.value
// Double = 2000. ...
always.value
// Double = 3000. ...

// Eval as a Monad

val greeting = Eval.
  always { println("Step 1"); "Hello" }.
  map { str => println("Step 2"); s"$str world" }

// greeting: cats.Eval[String] = cats.Eval$$anon$9@4fb887b9

greeting.value
// Step 1
// Step 2
// String = Hello world

val ans = for {
  a <- Eval.now { println("Calculating A"); 40 }
  b <- Eval.always { println("Calculating B"); 2 }
} yield {
  println("Adding A and B")
  a + b
}

// Calculating A
// ans: cats.Eval[Int] = cats.Eval$$anon$9@42762445

ans.value
// first access
/*
Calculating B
Adding A and B
res4: Int = 42
 */

ans.value
// second access
/*
Calculating B
Adding A and B
res5: Int = 42
 */

/*
Eval has a memoize method that allows us to memoize a chain of computations.
The result of the chain up to the call to memoize is cached,
whereas calculations a[er the call retain their original semantics:
 */

val saying = Eval.
  always { println("Step 1"); "The cat" }.
  map { str => println("Step 2"); s"$str sat on" }.
  memoize.
  map { str => println("Step 3"); s"$str the mat" }

// saying: cats.Eval[String] = cats.Eval$$anon$9@7b706240

saying.value
// first access
/*
Step 1
Step 2
Step 3
String = The cat sat on the mat
 */

saying.value
// second access
/*
Step 3
String = The cat sat on the mat
 */

// without memoize the second access would look like
/*
Step 1
Step 2
Step 3
String = The cat sat on the mat
 */

// Eval.defer

// stack safe as it is "trampolined"

def factorial1(n: BigInt): BigInt =
  if(n == 1) n else n * factorial1(n - 1)
// fairly easy to run out of stack space ^

def factorialSafe(n: BigInt): Eval[BigInt] =
  if(n == 1) {
    Eval.now(n)
  } else {
    Eval.defer(factorialSafe(n - 1).map(_ * n))
  }
factorialSafe(50000).value
// BigInt = 334732050959714483691...